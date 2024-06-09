package nl.han.oose.dea.datasource;

import nl.han.oose.dea.datasource.util.DatabaseProperties;
import nl.han.oose.dea.Login;
import nl.han.oose.dea.exeptions.VerkeerdeIngevoerdeGegevensException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    //TODO ik heb de execption in de Dao gedaan, omdat toen ik het in de service had staan veroorzaakte het problemen met de unittests.


    public List<Login> getLoginItems(DatabaseProperties databaseProperties, String username, String password) {
        List<Login> items = new ArrayList<>();
        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());
            PreparedStatement preparedStatement = connection.prepareStatement("select * from login where userName = ? and password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Login login = new Login(resultSet.getInt("token"), resultSet.getString("userName"), resultSet.getString("password"));
                items.add(login);
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
        if (items.isEmpty()) {
            throw new VerkeerdeIngevoerdeGegevensException();
        }

        return items;
    }

}
