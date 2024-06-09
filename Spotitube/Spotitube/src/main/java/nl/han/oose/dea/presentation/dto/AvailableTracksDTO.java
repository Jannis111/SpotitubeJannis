package nl.han.oose.dea.presentation.dto;

import nl.han.oose.dea.Track;

import java.util.List;

public class AvailableTracksDTO {
    private List<Track> tracks;

    public AvailableTracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public AvailableTracksDTO() {

    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }


}
