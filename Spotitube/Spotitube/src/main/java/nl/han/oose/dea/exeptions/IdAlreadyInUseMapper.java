package nl.han.oose.dea.exeptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IdAlreadyInUseMapper implements ExceptionMapper<IdAlreadyInUseException> {

    @Override
    public Response toResponse(IdAlreadyInUseException e){
        return Response.status(409).entity(e.getMessage()).type("text/plain").build();
    }
}
