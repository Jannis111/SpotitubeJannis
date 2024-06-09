package nl.han.oose.dea.exeptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoginMapper implements ExceptionMapper<LoginException> {

    @Override
    public Response toResponse(LoginException e){
        return Response.status(404).entity(e.getMessage()).type("text/plain").build();
    }
}
