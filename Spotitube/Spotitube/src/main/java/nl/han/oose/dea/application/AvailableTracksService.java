package nl.han.oose.dea.application;

import jakarta.inject.Inject;
import nl.han.oose.dea.datasource.TrackDao;
import nl.han.oose.dea.datasource.util.DatabaseProperties;
import nl.han.oose.dea.presentation.dto.AvailableTracksDTO;
import nl.han.oose.dea.Track;

import java.util.ArrayList;
import java.util.List;

public class AvailableTracksService {
    private List<Track> tracks = new ArrayList<>();

    private TrackDao trackDao;

    private DatabaseProperties databaseProperties;
    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {

        this.databaseProperties = databaseProperties;
    }

    @Inject
    public void setTrackDao(TrackDao trackDao) {

        this.trackDao = trackDao;
    }


    public AvailableTracksService(){
    }

    public AvailableTracksDTO haalHardcodedAvailableTracksOp(int playlistId) {
       tracks = trackDao.getAvailableTracks(databaseProperties, playlistId);
        AvailableTracksDTO availableTracksDTO = new AvailableTracksDTO(tracks);
        return availableTracksDTO;
    }
}
