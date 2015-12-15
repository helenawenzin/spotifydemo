package se.nackademin.spotifydemo.web;

import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.Image;
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

    private SpotifyAuthorizer spotifyAuthorizer = new SpotifyAuthorizer();
    private SpotifyAPI spotifyAPI = new SpotifyAPI(spotifyAuthorizer.getApi());

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) throws IOException, WebApiException {
        try {
            model.put("time", new Date());
            model.put("name", spotifyAPI.getUser().getDisplayName());
            model.put("playlists", spotifyAPI.getPlayLists());
            return "welcome";
        } catch (BadRequestException e) {
            if (e.getMessage().equals("401")) {
                System.out.println(e);
                System.out.println("I think we are logged out. Let's go to /login");
                return "redirect:/login";
            }
            throw new BadRequestException(e.getMessage());
        }
    }

    @RequestMapping("/playlist")
    public String playlist(@RequestParam("id") String id, Map<String, Object> model) throws IOException, WebApiException {
        model.put("name", spotifyAPI.getUser().getDisplayName());
        model.put("id", id);
        model.put("playlist", spotifyAPI.getPlaylist(id));
        return "playlist";
    }

    @RequestMapping("/artist")
    public String artist(@RequestParam("id") String id, Map<String, Object> model) throws IOException, WebApiException {
        model.put("name", spotifyAPI.getUser().getDisplayName());
        model.put("id", id);
        Artist artist = spotifyAPI.getArtist(id);
        model.put("artist", artist);
        model.put("image", getRightSizedImage(artist));
        model.put("albums", spotifyAPI.getAlbums(id));
        return "artist";
    }

    private Image getRightSizedImage(Artist artist) {
        for (Image image : artist.getImages()) {
            if (image.getWidth() == 200) {
                return image;
            }
        }
        return getDefaultImage();
    }

    private Image getDefaultImage() {
        Image image = new Image();
        image.setUrl("http://bookshelf.mml.ox.ac.uk/wp-uploads/2014/12/batman_silhouette_by_icedragon529.jpg");
        image.setWidth(200);
        image.setHeight(260);
        return image;
    }

    @RequestMapping("/about")
    @ResponseBody
    String about() {
        boolean isLoggedIn;
        try {
            spotifyAPI.getUser();
            isLoggedIn = true;
        } catch (Exception e) {
            isLoggedIn = false;
        }
        if (isLoggedIn) {
            return "You are logged in!";
        } else {
            return "You are not logged in!";
        }
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        String url = spotifyAuthorizer.getURL();
        return "redirect:" + url;
    }

    @RequestMapping(value="/logout")
    public String logout() {
        spotifyAuthorizer.getApi().setAccessToken("");
        spotifyAuthorizer.getApi().setRefreshToken("");
        return "redirect:/about";
    }

    @RequestMapping("/callback")
    String callback(@RequestParam("code") String code, @RequestParam String state) {
        System.out.println("We got a callback from Spotify!");
        //TODO: Assert state in a nicer way?
        if (!spotifyAuthorizer.state.equals(state)) {
            throw new RuntimeException("State check failed. Possible Cross Site Request Forgery.");
        }
        spotifyAuthorizer.getAndSetSpotifyTokens(code);
        return "redirect:/";
    }

}