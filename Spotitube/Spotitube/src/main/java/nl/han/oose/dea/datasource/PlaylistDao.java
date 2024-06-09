package nl.han.oose.dea.datasource;

import nl.han.oose.dea.datasource.util.DatabaseProperties;
import nl.han.oose.dea.Playlist;
import nl.han.oose.dea.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDao {
    public List<Playlist> getPlaylistItems(DatabaseProperties databaseProperties, String token, List<Track> tracks) {
        List<Playlist> items = new ArrayList<>();
        boolean isOwner;
        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            PreparedStatement preparedStatementVoorPlaylists = connection.prepareStatement("select token, id, name from playlists");


            ResultSet resultSet = preparedStatementVoorPlaylists.executeQuery();


            while (resultSet.next()) {
                if (resultSet.getString("token").equals(token)) {
                    isOwner = true;
                } else {
                    isOwner = false;
                }
                Playlist playlist = new Playlist(resultSet.getInt("id"), resultSet.getString("name"), isOwner, tracks);
                items.add(playlist);

            }

            resultSet.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
        return items;
    }


    public void deletePlaylist(DatabaseProperties databaseProperties, String token, int playlistId) {

        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM playlistTrack where playlistId = ? ");
            preparedStatement1.setInt(1, playlistId);


            PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM playlists WHERE token = ? and id = ?");
            preparedStatement2.setString(1, token);
            preparedStatement2.setInt(2, playlistId);

            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

    }

    public void updatePlaylist(DatabaseProperties databaseProperties, String token, Playlist playlistsDTO) {

        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            PreparedStatement preparedStatement = connection.prepareStatement("insert into playlists(token, name) values(?, ?) ");

            preparedStatement.setString(1, token);

            preparedStatement.setString(2, playlistsDTO.getName());

            preparedStatement.execute();


        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

    }

}
