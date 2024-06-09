package nl.han.oose.dea.presentation.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.application.AvailableTracksService;

@Path("/tracks")
public class TracksResource {

    AvailableTracksService availableTracksService;

    @Inject
    public void setAvailableTracksService(AvailableTracksService availableTracksService) {
        this.availableTracksService = availableTracksService;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAvailableTracks(@QueryParam("forPlaylist") int playlistId) {
        var resultaat = availableTracksService.haalHardcodedAvailableTracksOp(playlistId);
        return Response.status(200).entity(resultaat).build();
    }


}
