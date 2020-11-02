package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.UserSong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class UserSongDAO {
    private static final String SELECT_USER_SONGS_BY_ID = "SELECT id, id_user, id_song FROM pazyniuk.user_song WHERE id = ?;";
    private static final String SELECT_ALL_USER_SONGS = "SELECT id, id_user, id_song FROM pazyniuk.user_song;";
    private static final String DELETE_USER_SONGS_SQL = "DELETE FROM pazyniuk.user_song WHERE id = ?;";
    private static final String UPDATE_USER_SONGS_SQL = "UPDATE pazyniuk.user_song SET id_user = ?, id_song = ? WHERE id = ?;";
    private static final String INSERT_USER_SONGS_SQL = "INSERT INTO pazyniuk.user_song (id_user, id_song) VALUES (?, ?);";

    private UserSong modifyUserSong(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        int idUser = rs.getInt("id_user");
        int idSong = rs.getInt("id_song");

        UserSong userSong = new UserSong();
        userSong.setId(id);
        userSong.setIdUser(idUser);
        userSong.setIdSong(idSong);

        return userSong;
    }

    public final void insertUserSong(UserSong userSong) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SONGS_SQL)) {
            preparedStatement.setString(1, String.valueOf(userSong.getIdUser()));
            preparedStatement.setString(2, String.valueOf(userSong.getIdSong()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final UserSong selectUserSong(final int id) {
        UserSong userSong = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SONGS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                userSong = modifyUserSong(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userSong;
    }

    public final List<UserSong> selectAllUserSongs() {
        List<UserSong> userSongs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_SONGS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                UserSong userSong = modifyUserSong(rs);
                userSongs.add(userSong);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userSongs;
    }

    public final void deleteUserSongs(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SONGS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateUserSongs(UserSong userSong) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SONGS_SQL);) {
            preparedStatement.setString(1, String.valueOf(userSong.getIdUser()));
            preparedStatement.setString(2, String.valueOf(userSong.getIdSong()));
            preparedStatement.setInt(3, Math.toIntExact(userSong.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
