package nl.han.oose.dea.presentation.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.Login;
import nl.han.oose.dea.Playlist;
import nl.han.oose.dea.Track;
import nl.han.oose.dea.presentation.dto.PlaylistsDTO;
import nl.han.oose.dea.presentation.dto.TracksDTO;
import nl.han.oose.dea.presentation.resources.PlaylistResource;
import nl.han.oose.dea.application.PlaylistService;
import nl.han.oose.dea.application.TracksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PlaylistResourceTest {

    PlaylistResource sut;
    private PlaylistService mockedPlaylistService;

    private TracksService mockedTracksService;

    @BeforeEach
    public void setUp() {
        this.sut = new PlaylistResource();

        this.mockedTracksService = Mockito.mock(TracksService.class);
        this.mockedPlaylistService = Mockito.mock(PlaylistService.class);


        this.sut.setPlaylistService(mockedPlaylistService, mockedTracksService);
    }


    @Test
    public void whenGetPlaylistGetHaalPlaylistsOp() {
        var Json = sut.getPlaylist("0");

        Mockito.verify(mockedPlaylistService).haalPlaylistsOp("0");
    }

    @Test
    public void getPlaylistDTOFromHaalPlaylistsOp() {
        List<Track> tracks = new ArrayList<>();
        Playlist playlist = new Playlist(1, "Metal", true, tracks);
        List<Playlist> playlistList = new ArrayList<>();
        playlistList.add(playlist);
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.setPlaylists(playlistList);

        when(mockedPlaylistService.haalPlaylistsOp("1")).thenReturn(playlistsDTO);

        var Json = sut.getPlaylist("1");

        assertEquals((Json.getEntity()), playlistsDTO);
    }

    @Test
    public void getPlaylistResponseOk() {
        var expected = Response.Status.OK;
        List<Track> tracks = new ArrayList<>();
        Playlist playlist = new Playlist(1, "Metal", true, tracks);
        List<Playlist> playlistList = new ArrayList<>();
        playlistList.add(playlist);
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.setPlaylists(playlistList);

        when(mockedPlaylistService.haalPlaylistsOp("1")).thenReturn(playlistsDTO);

        var Json = sut.getPlaylist("1");

        assertEquals((expected.getStatusCode()), Json.getStatus());
    }

    @Test
    public void whenDeletePlaylistRunDeleteItem() {
        var Json = sut.deletePlaylist(1, "1");

        Mockito.verify(mockedPlaylistService).deleteItem("1", 1);
    }

    @Test
    public void whenDeletePlaylistRunHaalPlaylistsOp() {
        var Json = sut.deletePlaylist(1, "1");

        Mockito.verify(mockedPlaylistService).haalPlaylistsOp("1");
    }


    @Test
    public void whenAddPlaylistRunAddItem() {
        List<Track> tracks = new ArrayList<>();
        Playlist playlist = new Playlist(1, "Metal", true, tracks);


        var Json = sut.addPlaylist("1", playlist);

        Mockito.verify(mockedPlaylistService).addItem("1", playlist);
    }

    @Test
    public void whenEditPlaylistRunEditItem() {
        List<Track> tracks = new ArrayList<>();
        Playlist playlist = new Playlist(1, "Metal", true, tracks);


        var Json = sut.editPlaylist(1, "1", playlist);

        Mockito.verify(mockedPlaylistService).editItem("1", 1, playlist);
    }

    @Test
    public void whenGetTracksRunHaalTracksOp() {
        var Json = sut.getTracks(1, "1");

        Mockito.verify(mockedPlaylistService).haalTracksOp(1, "1");
    }

    @Test
    public void getTracksDTOFromGetTracks() {
        Track track = new Track(1, "test", "test", 200, "test", 30, "19-03-2006", "test", false);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.setTracks(tracks);

        when(mockedPlaylistService.haalTracksOp(1, "1")).thenReturn(tracksDTO);

        var Json = sut.getTracks(1,"1");

        assertEquals((Json.getEntity()), tracksDTO);
    }

    @Test
    public void getTracksResponseOk() {
        var expected = Response.Status.OK;

        Track track = new Track(1, "test", "test", 200, "test", 30, "19-03-2006", "test", false);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.setTracks(tracks);

        when(mockedPlaylistService.haalTracksOp(1, "1")).thenReturn(tracksDTO);

        var Json = sut.getTracks(1,"1");

        assertEquals((Json.getStatus()), expected.getStatusCode());
    }

    @Test
    public void whenAddTrackPlaylistRunEditTracks() {
        Track track = new Track(1, "test", "test", 200, "test", 30, "19-03-2006", "test", false);

        var Json = sut.addTrackPlaylist(1, "1", track);

        Mockito.verify(mockedPlaylistService).editTracks("1", 1, track);
    }
    @Test
    public void whenAddTrackPlaylistRunHaalTracksOp() {
        Track track = new Track(1, "test", "test", 200, "test", 30, "19-03-2006", "test", false);

        var Json = sut.addTrackPlaylist(1, "1", track);

        Mockito.verify(mockedPlaylistService).haalTracksOp(1, "1");
    }

    @Test
    public void whenRemoveTrackRunRemoveTrack() {
        var Json = sut.removeTrack(1, 1,"1");

        Mockito.verify(mockedPlaylistService).removeTrack("1", 1, 1);
    }
    @Test
    public void whenRemoveTrackHaalTracksOp() {
        var Json = sut.removeTrack(1, 1,"1");

        Mockito.verify(mockedPlaylistService).haalTracksOp(1, "1");
    }
}
