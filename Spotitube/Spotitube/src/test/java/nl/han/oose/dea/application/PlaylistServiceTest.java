package nl.han.oose.dea.application;

import nl.han.oose.dea.datasource.PlaylistDao;
import nl.han.oose.dea.datasource.PlaylistDaoTest;
import nl.han.oose.dea.datasource.TrackDao;
import nl.han.oose.dea.datasource.util.DatabaseProperties;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistServiceTest {
    private PlaylistService sut;
    private DatabaseProperties databaseProperties = new DatabaseProperties();

    private PlaylistDao mockedPlaylistDao;

    private TrackDao mockedTrackDao;

    @BeforeEach
    public void setUp() {
        this.sut = new PlaylistService();


        this.mockedPlaylistDao = Mockito.mock(PlaylistDao.class);
        this.mockedTrackDao = Mockito.mock(TrackDao.class);

        this.sut.setDatabaseProperties(databaseProperties);
        this.sut.setPlaylistDao(mockedPlaylistDao);
        this.sut.setTrackDao(mockedTrackDao);
    }
//Todo het is mij niet gelukt om deze unitest te maken.

//    @Test
//    public void whenHaalPlaylistsOpRunGetPlaylistItems() {
//        Track track = new Track(1,"Song for someone", "The Frames", 350, "The cost", 0, "", null, false);
//        Track track2 = new Track(2, "The cost", "The Frames", 423, null, 37,  "19-03-2006", "Title song from the Album The Cost", true);
//        List<Track> tracks = new ArrayList<>();
//        tracks.add(track);
//        tracks.add(track2);
//
//        var Json = sut.haalPlaylistsOp("1");
//
//        Mockito.verify(mockedPlaylistDao).getPlaylistItems(databaseProperties, "1", tracks);
//    }

//    @Test
//    public void getPlaylistVanPlaylistDao() {
//
//
//        Track track = new Track(1,"Song for someone", "The Frames", 350, "The cost", 0, "", null, false);
//        Track track2 = new Track(2, "The cost", "The Frames", 423, null, 37,  "19-03-2006", "Title song from the Album The Cost", true);
//        List<Track> tracks = new ArrayList<>();
//        tracks.add(track);
//        tracks.add(track2);
//        Playlist playlist = new Playlist(1, "Heavy Metal", true, tracks);
//        List<Playlist> playlistList = new ArrayList<>();
//        playlistList.add(playlist);
//PlaylistsDTO playlistsDTO = new PlaylistsDTO();
//        when(mockedPlaylistDao.getPlaylistItems (databaseProperties, "1", tracks)).thenReturn(playlistList);
//        playlistsDTO.setPlaylists(playlistList);
//        var Json = sut.haalPlaylistsOp("1");
//
//
//        assertEquals(Json,playlistsDTO);
//    }




}
