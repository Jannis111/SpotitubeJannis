package nl.han.oose.dea.datasource;


import nl.han.oose.dea.Track;
import nl.han.oose.dea.datasource.util.DatabaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDaoTest {

    private PlaylistDao sut = new PlaylistDao();

    private DatabaseProperties databaseProperties = new DatabaseProperties();


    @Test
    public void whenGetGetPlaylistItemsReturnInformatie(){
        Track track = new Track(1,"Song for someone", "The Frames", 350, "The cost", 0, "", null, false);
        Track track2 = new Track(2, "The cost", "The Frames", 423, null, 37,  "19-03-2006", "Title song from the Album The Cost", true);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        tracks.add(track2);

        var Json = sut.getPlaylistItems(databaseProperties, "1", tracks);

        Assertions.assertNotNull(Json);


    }

    @Test
    public void deletePlaylist(){
        Track track = new Track(1,"Song for someone", "The Frames", 350, "The cost", 0, "", null, false);
        Track track2 = new Track(2, "The cost", "The Frames", 423, null, 37,  "19-03-2006", "Title song from the Album The Cost", true);
        List<Track> tracks = new ArrayList<>();
        tracks.add(track);
        tracks.add(track2);

        var Json = sut.getPlaylistItems(databaseProperties, "1", tracks);

        Assertions.assertNotNull(Json);


    }



}
