package nl.han.oose.dea.application;

import nl.han.oose.dea.Track;

import java.util.ArrayList;
import java.util.List;

public class TracksService {
    private List<Track> hardCodedTracks = new ArrayList<>();


    public TracksService() {
        //hardCodedTracks.add(new Track(1, "Song for someone", "The Frames", 350, "The cost", 0, null, null, false));
        //hardCodedTracks.add(new Track(2, "The cost", "The Frames", 423, null, 37, "19-03-2006", "Title song from the Album The Cost", true));

    }

    public List<Track> haalTracksOp() {

        return hardCodedTracks;
    }


    public void voegTrackToe(Track track) {
        hardCodedTracks.add(track);

    }


}
