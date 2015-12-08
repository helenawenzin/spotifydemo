package se.nackademin.spotifydemo.api;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.methods.PlaylistRequest;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.User;

import java.io.IOException;

public class SpotifyAPI {

    private Api api;

    public SpotifyAPI(Api api) {
        this.api = api;
    }

    public void setAPI(Api api) {
        this.api = api;
    }

    public Album getAlbum() {

        // Create a request object for the type of request you want to make
        AlbumRequest request = api.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").build();

        // Retrieve an album
        Album album;
        try {
            album = request.get();
        } catch (Exception e) {
            //TODO: Use a log framework (log4j?)
            System.out.println("Could not get albums.");
            return new Album();
        }
        return album;
    }

    public User getUser() throws IOException, WebApiException {
        return api.getMe().build().get();
    }

    public Playlist getPlaylist() throws IOException, WebApiException {
        User user = getUser();
        String userID = user.getId();
        return api.getPlaylist(userID, "0l6DqYMkgeqrkdM8EEB0YE")
                .build()
                .get();
    }
}

