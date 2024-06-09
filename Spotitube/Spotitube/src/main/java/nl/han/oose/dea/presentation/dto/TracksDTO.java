package nl.han.oose.dea.presentation.dto;

import nl.han.oose.dea.Track;

import java.util.List;

public class TracksDTO {

    private List<Track> tracks;

    public TracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public TracksDTO() {

    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
