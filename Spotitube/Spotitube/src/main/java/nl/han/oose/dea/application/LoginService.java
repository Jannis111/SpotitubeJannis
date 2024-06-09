package nl.han.oose.dea.application;

import jakarta.inject.Inject;
import nl.han.oose.dea.Login;
import nl.han.oose.dea.datasource.LoginDao;
import nl.han.oose.dea.datasource.util.DatabaseProperties;


import java.util.ArrayList;
import java.util.List;

public class LoginService {
    private List<Login> items = new ArrayList<>();

    private LoginDao loginDao;

    private DatabaseProperties databaseProperties;


    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Inject
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }


    public LoginService() {

    }

    public Login haalLoginGevenOp(String user, String password) {

        items = loginDao.getLoginItems(databaseProperties, user, password);



        Login requestedItem = items.stream().filter((item) -> item.getUser().equals(user)).filter((item) -> item.getPassword().equals(password)).findFirst().orElse(null);

        return requestedItem;
    }


}
