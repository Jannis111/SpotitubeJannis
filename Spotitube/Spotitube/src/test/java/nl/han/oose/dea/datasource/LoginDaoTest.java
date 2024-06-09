package nl.han.oose.dea.datasource;

import nl.han.oose.dea.application.PlaylistService;
import nl.han.oose.dea.datasource.util.DatabaseProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginDaoTest {

    private LoginDao sut = new LoginDao();

    private DatabaseProperties databaseProperties = new DatabaseProperties();


  @Test
    public void whenGetLoginItemsReturnInformatie(){


       var Json = sut.getLoginItems(databaseProperties, "Jannis", "Geerts");

      Assertions.assertNotNull(Json);


  }



}
