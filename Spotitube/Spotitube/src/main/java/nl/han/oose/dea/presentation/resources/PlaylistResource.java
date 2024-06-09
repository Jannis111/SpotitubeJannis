package nl.han.oose.dea.presentation.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.Track;
import nl.han.oose.dea.application.PlaylistService;
import nl.han.oose.dea.Playlist;
import nl.han.oose.dea.application.TracksService;

@Path("/playlists")
public class PlaylistResource {
    PlaylistService playlistService;
    TracksService tracksService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService, TracksService tracksService) {
        this.playlistService = playlistService;
        this.tracksService = tracksService;
    }

    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@QueryParam("token") String token) {

        var resultaat = playlistService.haalPlaylistsOp(token);
        return Response.status(200).entity(resultaat).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.deleteItem(token, id);
        var resultaat = playlistService.haalPlaylistsOp(token);
        return Response.status(200).entity(resultaat).build();
    }

    @POST
    @Consumes
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, Playlist newPlayList) {
        playlistService.addItem(token, newPlayList);
        var resultaat = playlistService.haalPlaylistsOp(token);
        return Response.status(201).entity(resultaat).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response editPlaylist(@PathParam("id") int id, @QueryParam("token") String token, Playlist newPlayList) {
        playlistService.editItem(token, id, newPlayList);
        var resultaat = playlistService.haalPlaylistsOp(token);
        return Response.status(201).entity(resultaat).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response getTracks(@PathParam("id") int id, @QueryParam("token") String token) {
        var resultaat = playlistService.haalTracksOp(id, token);
        return Response.status(200).entity(resultaat).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response addTrackPlaylist(@PathParam("id") int id, @QueryParam("token") String token, Track track) {
        playlistService.editTracks(token, id, track);
        var resultaat = playlistService.haalTracksOp(id, token);
        return Response.status(201).entity(resultaat).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks/{id2}")
    public Response removeTrack(@PathParam("id") int id, @PathParam("id2") int id2, @QueryParam("token") String token) {
        playlistService.removeTrack(token, id, id2);
        var resultaat = playlistService.haalTracksOp(id, token);
        return Response.status(200).entity(resultaat).build();
    }

}
