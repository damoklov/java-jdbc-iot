package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class AlbumDAO {
    private static final String SELECT_ALBUMS_BY_ID = "SELECT id, name, year FROM pazyniuk.album WHERE id = ?;";
    private static final String SELECT_ALL_ALBUMS = "SELECT id, name, year FROM pazyniuk.album;";
    private static final String DELETE_ALBUMS_SQL = "DELETE FROM pazyniuk.album WHERE id = ?;";
    private static final String UPDATE_ALBUMS_SQL = "UPDATE pazyniuk.album SET name = ?, year = ? WHERE id = ?;";
    private static final String INSERT_ALBUMS_SQL = "INSERT INTO pazyniuk.album (name, year) VALUES (?, ?);";

    private Album modifyAlbum(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String name = rs.getString("name");
        String year = rs.getString("year");

        Album album = new Album();
        album.setId(id);
        album.setName(name);
        album.setYear(year);

        return album;
    }

    public final void insertAlbum(Album album) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALBUMS_SQL)) {
            preparedStatement.setString(1, String.valueOf(album.getName()));
            preparedStatement.setString(2, String.valueOf(album.getYear()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final Album selectAlbum(final int id) {
        Album album = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALBUMS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                album = modifyAlbum(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return album;
    }

    public final List<Album> selectAllAlbums() {
        List<Album> albums = new ArrayList<>();

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ALBUMS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Album album = modifyAlbum(rs);
                albums.add(album);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return albums;
    }

    public final void deleteAlbum(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALBUMS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateAlbum(Album album) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ALBUMS_SQL);) {
            preparedStatement.setString(1, album.getName());
            preparedStatement.setString(2, String.valueOf(album.getYear()));
            preparedStatement.setInt(3, Math.toIntExact(album.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
