package nl.han.oose.dea.exeptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class VerkeerdeIngevoerdeGegevensMapper implements ExceptionMapper<VerkeerdeIngevoerdeGegevensException> {
    @Override
    public Response toResponse(VerkeerdeIngevoerdeGegevensException e) {
        return Response.status(401).entity(e.getMessage()).type("text/plain").build();
    }
}
