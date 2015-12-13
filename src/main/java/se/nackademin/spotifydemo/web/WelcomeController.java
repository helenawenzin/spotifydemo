package se.nackademin.spotifydemo.web;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Album;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.nackademin.spotifydemo.api.SpotifyAPI;
import se.nackademin.spotifydemo.api.SpotifyAuthorizer;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class WelcomeController {

    private String message = "Hejsan!";
    private SpotifyAuthorizer spotifyAuthorizer = new SpotifyAuthorizer();
    private SpotifyAPI spotifyAPI = new SpotifyAPI(spotifyAuthorizer.getApi());

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) throws IOException, WebApiException {
        try {
            model.put("time", new Date());
            model.put("message", this.message);
            Album album = spotifyAPI.getAlbum();
            model.put("album", album.getName() + " by " + album.getArtists().get(0).getName());
            model.put("name", spotifyAPI.getUser().getDisplayName());
            model.put("playlist", spotifyAPI.getPlaylist().getName());
            model.put("playlists", spotifyAPI.getPlayLists());
            return "welcome";
        } catch (BadRequestException e) {
            return "redirect:/login";
        }
    }

    @RequestMapping("/about")
    @ResponseBody
    String about() {
        return "Helena är bäst";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        String url = spotifyAuthorizer.getURL();
        return "redirect:" + url;
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout() {
        return "redirect:/about";
    }


    @RequestMapping("/callback")
    String callback(@RequestParam("code") String code, @RequestParam String state) {
        //TODO: Assert state in a nicer way?
        if (!spotifyAuthorizer.state.equals(state)) {
            throw new RuntimeException("State check failed. Possible Cross Site Request Forgery.");
        }
        spotifyAuthorizer.getAndSetSpotifyTokens(code);
        return "redirect:/";
    }

}