package nl.han.oose.dea.exeptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataNietGevondenMapper implements ExceptionMapper<DataNietGevondenException> {

@Override
public Response toResponse(DataNietGevondenException e){
        return Response.status(404).entity(e.getMessage()).type("text/plain").build();
        }


}
