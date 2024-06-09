package nl.han.oose.dea.application;

import nl.han.oose.dea.Login;
import nl.han.oose.dea.datasource.LoginDao;
import nl.han.oose.dea.datasource.util.DatabaseProperties;
import nl.han.oose.dea.presentation.resources.PlaylistResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    LoginService sut;

    private DatabaseProperties databaseProperties = new DatabaseProperties();

    private LoginDao mockedLoginDao;

    @BeforeEach
    public void setUp() {
        this.sut = new LoginService();



        this.mockedLoginDao = Mockito.mock(LoginDao.class);

        this.sut.setDatabaseProperties(databaseProperties);
        this.sut.setLoginDao(mockedLoginDao);

    }

    @Test
    public void whenHaalLoginGevenOpRunGetLoginItems() {
        var Json = sut.haalLoginGevenOp("Jannis", "Geerts");

        Mockito.verify(mockedLoginDao).getLoginItems(databaseProperties, "Jannis", "Geerts");
    }
    @Test
    public void getItemsVanLoginDao() {
        List<Login> loginList = new ArrayList<>();
        Login items = new Login();
        items.setUser("Jannis");
        items.setPassword("Geerts");
        items.setToken(1);
        loginList.add(items);
        when(mockedLoginDao.getLoginItems (databaseProperties, "Jannis", "Geerts")).thenReturn(loginList);

        var Json = sut.haalLoginGevenOp("Jannis", "Geerts");


        assertEquals(Json, loginList.get(0));
    }



}
