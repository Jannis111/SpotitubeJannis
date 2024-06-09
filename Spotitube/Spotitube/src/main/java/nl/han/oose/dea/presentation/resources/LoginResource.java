package nl.han.oose.dea.presentation.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.application.LoginService;
import nl.han.oose.dea.Login;

import static jakarta.ws.rs.core.Response.ok;

@Path("/login")
public class LoginResource {
    LoginService loginService;

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        var result = loginService.haalLoginGevenOp(login.getUser(), login.getPassword());
        return Response.status(200).entity(result).build();


    }

}


