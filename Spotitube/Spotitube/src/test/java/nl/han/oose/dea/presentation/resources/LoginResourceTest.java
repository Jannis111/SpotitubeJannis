package nl.han.oose.dea.presentation.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.exeptions.DataNietGevondenException;
import nl.han.oose.dea.presentation.resources.LoginResource;
import nl.han.oose.dea.application.LoginService;
import nl.han.oose.dea.Login;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginResourceTest {
    LoginResource sut;
    private LoginService mockedLoginService;

    @BeforeEach
    public void setUp() {
        this.sut = new LoginResource();

        this.mockedLoginService = Mockito.mock(LoginService.class);

        this.sut.setLoginService(mockedLoginService);
    }

    @Test
    public void whenGetLoginGetHaalLoginGegevenOp() {
        var Json = sut.login(new Login(1, "Jannis", "Geerts"));

        Mockito.verify(mockedLoginService).haalLoginGevenOp("Jannis", "Geerts");
    }

    @Test
    public void getLoginObjectFromLoginService() {
        Login items = new Login();
        items.setUser("Jannis");
        items.setPassword("Geerts");
        items.setToken(1);
        when(mockedLoginService.haalLoginGevenOp("Jannis", "Geerts")).thenReturn(items);

        var Json = sut.login(new Login(1, "Jannis", "Geerts"));

        assertEquals((Json.getEntity()), items);
    }

    @Test
    public void loginResponseOk() {
        var expected = Response.Status.OK;
        Login items = new Login();
        items.setUser("Jannis");
        items.setPassword("Geerts");
        items.setToken(1);
        when(mockedLoginService.haalLoginGevenOp("Jannis", "Geerts")).thenReturn(items);

        var result = sut.login(new Login(1, "Jannis", "Geerts"));

        Assertions.assertEquals(expected.getStatusCode(), result.getStatus());

    }
//TODO de fout response uni testen.
  /*  @Test
    public void loginResponseUnauthorized() {
        var expected = Response.Status.UNAUTHORIZED;
        Login items = new Login();
        items.setUser("Jannis");
        items.setPassword("Geerts");
        items.setToken(1);
        when(mockedLoginService.haalLoginGevenOp("J", "J")).thenThrow(new DataNietGevondenException());

        var result = sut.login(new Login(1, "J", "J"));

        Assertions.assertEquals(expected.getStatusCode(), result.getStatus());

    }
*/
}
