package se.nackademin.spotifydemo.api;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.methods.PlaylistRequest;
import com.wrapper.spotify.methods.UserPlaylistsRequest;
import com.wrapper.spotify.models.*;

import java.io.IOException;
import java.util.List;

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

    public Playlist getPlaylist(String playlistId) throws IOException, WebApiException {
        User user = getUser();
        String userID = user.getId();
        return api.getPlaylist(userID, playlistId)
                .build()
                .get();
    }

    public List<SimplePlaylist> getPlayLists() throws IOException, WebApiException {
        final UserPlaylistsRequest request = api.getPlaylistsForUser(getUser().getId()).build();
        final Page<SimplePlaylist> playlistsPage = request.get();
        return playlistsPage.getItems();
    }

    public Artist getArtist(String id) throws IOException, WebApiException {
        return api.getArtist(id).build().get();
    }

    public String getUsersPlaylists() throws IOException, WebApiException {

        String playlistsNames = "";
        User user = getUser();
        String userID = user.getId();

        final UserPlaylistsRequest request = api.getPlaylistsForUser(userID).build();

        try {
            final Page<SimplePlaylist> playlistsPage = request.get();

            for (SimplePlaylist playlist : playlistsPage.getItems()) {
                playlistsNames = playlistsNames + " : " + playlist.getName();
                System.out.println(playlist.getName());
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }

        return playlistsNames;
    }
}

