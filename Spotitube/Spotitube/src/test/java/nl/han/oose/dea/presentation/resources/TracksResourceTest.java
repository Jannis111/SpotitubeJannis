package nl.han.oose.dea.presentation.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.Track;
import nl.han.oose.dea.application.AvailableTracksService;
import nl.han.oose.dea.application.PlaylistService;
import nl.han.oose.dea.application.TracksService;
import nl.han.oose.dea.presentation.dto.AvailableTracksDTO;
import nl.han.oose.dea.presentation.resources.PlaylistResource;
import nl.han.oose.dea.presentation.resources.TracksResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TracksResourceTest {

    TracksResource sut;

    private AvailableTracksService mockedAvailableTracksService;

    @BeforeEach
    public void setUp() {
        this.sut = new TracksResource();

        this.mockedAvailableTracksService = Mockito.mock(AvailableTracksService.class);

        this.sut.setAvailableTracksService(mockedAvailableTracksService);
    }

    @Test
    public void whenGetAvailableTracksRunHaalHardcodedAvailableTracksOp() {
        var Json = sut.getAvailableTracks(1);

        Mockito.verify(mockedAvailableTracksService).haalHardcodedAvailableTracksOp(1);
    }

    @Test
    public void getAvailableTracksDTOFromAvailableTracks() {
        Track track = new Track(1, "test", "test", 200, "test", 30, "19-03-2006", "test", false);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        AvailableTracksDTO availableTracksDTO = new AvailableTracksDTO();
        availableTracksDTO.setTracks(tracks);

        when(mockedAvailableTracksService.haalHardcodedAvailableTracksOp(1)).thenReturn(availableTracksDTO);
        var Json = sut.getAvailableTracks(1);

        assertEquals((Json.getEntity()), availableTracksDTO);
    }

    @Test
    public void AvailableTracksResponseOk() {
        var expected = Response.Status.OK;
        Track track = new Track(1, "test", "test", 200, "test", 30, "19-03-2006", "test", false);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        AvailableTracksDTO availableTracksDTO = new AvailableTracksDTO();
        availableTracksDTO.setTracks(tracks);

        when(mockedAvailableTracksService.haalHardcodedAvailableTracksOp(1)).thenReturn(availableTracksDTO);
        var Json = sut.getAvailableTracks(1);

        assertEquals((Json.getStatus()), expected.getStatusCode());
    }
}
