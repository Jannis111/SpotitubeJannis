package nl.han.oose.dea.application;

import jakarta.inject.Inject;
import nl.han.oose.dea.Playlist;
import nl.han.oose.dea.datasource.TrackDao;
import nl.han.oose.dea.exeptions.DataNietGevondenException;
import nl.han.oose.dea.presentation.dto.PlaylistsDTO;
import nl.han.oose.dea.datasource.PlaylistDao;
import nl.han.oose.dea.datasource.util.DatabaseProperties;
import nl.han.oose.dea.Track;
import nl.han.oose.dea.presentation.dto.TracksDTO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private List<Playlist> meerderePlayListItem = new ArrayList<>();

    PlaylistsDTO playlistsDTO = new PlaylistsDTO();

    TracksService tracksService = new TracksService();

    PlaylistDao playlistDao;

    TrackDao trackDao;

    DatabaseProperties databaseProperties;

    private int lengte;

    @Inject
    public void setPlaylistDao(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    @Inject
    public void setTrackDao(TrackDao trackDao) {

        this.trackDao = trackDao;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {

        this.databaseProperties = databaseProperties;
    }

    public PlaylistService() {

    }
    public PlaylistsDTO haalPlaylistsOp(String token) {

        meerderePlayListItem = playlistDao.getPlaylistItems(databaseProperties, token, tracksService.haalTracksOp());

        meerderePlayListItem.forEach(meerderePlayListItem -> {
            berekentLength(token, meerderePlayListItem.getId());
            playlistsDTO.setLength(lengte);
        });
        playlistsDTO.setPlaylists(meerderePlayListItem);


        return playlistsDTO;
    }


    public void berekentLength(String token, int id) {
        haalTracksOp(id, token).getTracks().forEach(track -> lengte += track.getDuration());
    }


    public void deleteItem(String token, int id) {
        playlistDao.deletePlaylist(databaseProperties, token, id);
    }


    public void addItem(String token, Playlist playlistsDTO) {
        meerderePlayListItem = haalPlaylistsOp(token).getPlaylists();
        playlistDao.updatePlaylist(databaseProperties, token, playlistsDTO);
    }

    public void editItem(String token, int id, Playlist newPlaylistsDTO) {
        meerderePlayListItem = haalPlaylistsOp(token).getPlaylists();
        deleteItem(token, id);
        addItem(token, newPlaylistsDTO);
    }

    public TracksDTO haalTracksOp(int id, String token) {
        TracksDTO tracksDTO = new TracksDTO(trackDao.getTracks(databaseProperties, id));
        return tracksDTO;
    }

    public void editTracks(String token, int id, Track track) {
        trackDao.addTrack(databaseProperties, id, track);
    }

    public void removeTrack(String token, int playListId, int trackId) {
        trackDao.deleteTrack(databaseProperties, playListId, trackId);
    }

}
