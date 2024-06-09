package nl.han.oose.dea.datasource;

import nl.han.oose.dea.Playlist;
import nl.han.oose.dea.Track;
import nl.han.oose.dea.datasource.util.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDao {

    public List<Track> getTracks(DatabaseProperties databaseProperties, int playlistId) {
        List<Track> items = new ArrayList<>();

        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            //preparedStatementVoorId.setString(1, token);


            PreparedStatement preparedStatementVoorTracks = connection.prepareStatement("SELECT track.id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable from playlistTrack INNER JOIN playlists ON playlistTrack.playlistId = playlists.id INNER JOIN track ON playlistTrack.trackId = track.id where playlistId = ?");

            preparedStatementVoorTracks.setInt(1, playlistId);

            ResultSet resultSet = preparedStatementVoorTracks.executeQuery();


            while (resultSet.next()) {

                Track track = new Track(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("performer"), resultSet.getInt("duration"),
                        resultSet.getString("album"), resultSet.getInt("playcount"),
                        resultSet.getString("publicationDate"), resultSet.getString("description"),
                        resultSet.getBoolean("offlineAvailable"));
                items.add(track);

            }

            resultSet.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
        return items;
    }


    public List<Track> getAvailableTracks(DatabaseProperties databaseProperties, int playlistId) {
        List<Track> items = new ArrayList<>();

        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());

            //preparedStatementVoorId.setString(1, token);


            PreparedStatement preparedStatementVoorTracks = connection.prepareStatement("SELECT * FROM track WHERE id NOT IN (SELECT trackId FROM playlistTrack WHERE playlistId = ?)");

            preparedStatementVoorTracks.setInt(1, playlistId);

            ResultSet resultSet = preparedStatementVoorTracks.executeQuery();


            while (resultSet.next()) {

                Track track = new Track(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("performer"), resultSet.getInt("duration"),
                        resultSet.getString("album"), resultSet.getInt("playcount"),
                        resultSet.getString("publicationDate"), resultSet.getString("description"),
                        resultSet.getBoolean("offlineAvailable"));
                items.add(track);

            }

            resultSet.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
        return items;
    }
//TODO aanpassen van offlineAvailable
    public void addTrack(DatabaseProperties databaseProperties, int playlistId, Track track) {
        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());



            PreparedStatement preparedStatementVoorUpdatenVanOfflineAvailable = connection.prepareStatement("UPDATE track SET offlineAvailable = ? where id = ?");

            preparedStatementVoorUpdatenVanOfflineAvailable.setBoolean(1, track.isOfflineAvailable());

            preparedStatementVoorUpdatenVanOfflineAvailable.setInt(2, track.getId());

            PreparedStatement preparedStatementVoorTracks = connection.prepareStatement("insert into playlistTrack values(?, ?)");

            preparedStatementVoorTracks.setInt(1, playlistId);

            preparedStatementVoorTracks.setInt(2, track.getId());

            preparedStatementVoorUpdatenVanOfflineAvailable.executeUpdate();
            preparedStatementVoorTracks.executeUpdate();


        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

    }


    public void deleteTrack(DatabaseProperties databaseProperties, int playlistId, int trackId) {
        try {
            Class.forName(databaseProperties.getDriver());
            Connection connection = DriverManager.getConnection(databaseProperties.getConnectionString());



            PreparedStatement preparedStatementVoorDelete = connection.prepareStatement("DELETE FROM playlistTrack WHERE playlistId = ? and trackId = ?");

            preparedStatementVoorDelete.setInt(1, playlistId);

            preparedStatementVoorDelete.setInt(2, trackId);



            preparedStatementVoorDelete.executeUpdate();



        } catch (SQLException e) {

            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {

            throw new RuntimeException(e);
        }

    }

}
