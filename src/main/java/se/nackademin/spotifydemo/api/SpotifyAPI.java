package se.nackademin.spotifydemo.api;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AlbumsForArtistRequest;
import com.wrapper.spotify.methods.UserPlaylistsRequest;
import com.wrapper.spotify.models.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SpotifyAPI {

    private Api api;

    public SpotifyAPI(Api api) {
        this.api = api;
    }

    public void setAPI(Api api) {
        this.api = api;
    }

    public List<SimpleAlbum> getAlbums(String id) throws IOException, WebApiException {
        return getAlbumsFilteredByType(id, AlbumType.ALBUM);
    }

    public List<SimpleAlbum> getSingles(String id) throws IOException, WebApiException {
        return getAlbumsFilteredByType(id, AlbumType.SINGLE);
    }

    private List<SimpleAlbum> getAlbumsFilteredByType(String artistId, AlbumType type) throws IOException, WebApiException {
        return getAlbumsAndSingles(artistId)
                .stream()
                .filter(item -> item.getAlbumType().equals(type))
                .collect(Collectors.toList());
    }

    public List<SimpleAlbum> getAlbumsAndSingles(String id) throws IOException, WebApiException {
        final AlbumsForArtistRequest request = api.getAlbumsForArtist(id).build();
        final Page<SimpleAlbum> albumsFromArtist = request.get();
        return albumsFromArtist.getItems();
    }

    public User getUser() throws IOException, WebApiException {
        return api.getMe().build().get();
    }

    public String getUserDisplayName() throws IOException, WebApiException {
        return getUser().getDisplayName() != null ? getUser().getDisplayName() : getUser().getId();
    }

    public Playlist getPlaylist(String playlistId) throws IOException, WebApiException {
        User user = getUser();
        String userID = user.getId();
        return api.getPlaylist(userID, playlistId)
                .build()
                .get();
    }

    public Playlist getPlaylist(String userID, String playlistId) throws IOException, WebApiException {
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

}

