package se.nackademin.spotifydemo.api;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.models.Album;

public class SpotifyAPI {

    public Album getAlbum() {

        // Create an API instance. The default instance connects to https://api.spotify.com/.
        Api api = Api.DEFAULT_API;

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
}
