package nl.han.oose.dea.presentation.dto;

import nl.han.oose.dea.Playlist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsDTO {


    private List<Playlist> playlists = new ArrayList<>();

    private int length;

    public void setLength(int length) {
        this.length = length;
    }


    public int getLength() {
        return length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }


}
