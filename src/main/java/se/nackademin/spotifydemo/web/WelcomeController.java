package se.nackademin.spotifydemo.web;

import com.wrapper.spotify.models.Album;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.nackademin.spotifydemo.api.SpotifyAPI;

import java.util.Date;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class WelcomeController {

    private String message = "Hejsan!";
    private SpotifyAPI spotifyAPI = new SpotifyAPI();

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        Album album = spotifyAPI.getAlbum();
        model.put("album", album.getName() + " by " + album.getArtists().get(0).getName());
        return "welcome";
    }

    @RequestMapping("/about")
    @ResponseBody
    String about() {
        return "Helena är bäst";
    }
}