package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class ArtistDAO {
    private static final String SELECT_ARTISTS_BY_ID = "SELECT id, name, country, gender FROM pazyniuk.artist WHERE id = ?;";
    private static final String SELECT_ALL_ARTISTS = "SELECT id, name, country, gender FROM pazyniuk.artist;";
    private static final String DELETE_ARTISTS_SQL = "DELETE FROM pazyniuk.artist WHERE id = ?;";
    private static final String UPDATE_ARTISTS_SQL = "UPDATE pazyniuk.artist SET name = ?, country = ?, gender = ? WHERE id = ?;";
    private static final String INSERT_ARTISTS_SQL = "INSERT INTO pazyniuk.artist (name, country, gender) VALUES (?, ?, ?);";

    private Artist modifyArtist(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String name = rs.getString("name");
        int country = rs.getInt("country");
        int gender = rs.getInt("gender");

        Artist artist = new Artist();
        artist.setId(id);
        artist.setName(name);
        artist.setCountry(country);
        artist.setGender(gender);

        return artist;
    }

    public final void insertArtist(Artist artist) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTISTS_SQL)) {
            preparedStatement.setString(1, String.valueOf(artist.getName()));
            preparedStatement.setString(2, String.valueOf(artist.getCountry()));
            preparedStatement.setString(3, String.valueOf(artist.getGender()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final Artist selectArtist(final int id) {
        Artist artist = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTISTS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                artist = modifyArtist(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return artist;
    }

    public final List<Artist> selectAllArtists() {
        List<Artist> artists = new ArrayList<>();

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTISTS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Artist artist = modifyArtist(rs);
                artists.add(artist);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return artists;
    }

    public final void deleteArtist(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTISTS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateArtist(Artist artist) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTISTS_SQL);) {
            preparedStatement.setString(1, artist.getName());
            preparedStatement.setString(2, String.valueOf(artist.getCountry()));
            preparedStatement.setString(3, String.valueOf(artist.getGender()));
            preparedStatement.setInt(4, Math.toIntExact(artist.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
