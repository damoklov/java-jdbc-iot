package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class GenreDAO {
    private static final String SELECT_GENRES_BY_ID = "SELECT id, name FROM pazyniuk.genre WHERE id = ?;";
    private static final String SELECT_ALL_GENRES = "SELECT id, name FROM pazyniuk.genre;";
    private static final String DELETE_GENRES_SQL = "DELETE FROM pazyniuk.genre WHERE id = ?;";
    private static final String UPDATE_GENRES_SQL = "UPDATE pazyniuk.genre SET name = ? WHERE id = ?;";
    private static final String INSERT_GENRES_SQL = "INSERT INTO pazyniuk.genre (name) VALUES (?);";

    private Genre modifyGenre(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String name = rs.getString("name");

        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);

        return genre;
    }

    public final void insertGenre(Genre genre) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GENRES_SQL)) {
            preparedStatement.setString(1, String.valueOf(genre.getName()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final Genre selectGenre(final int id) {
        Genre genre = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENRES_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                genre = modifyGenre(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public final List<Genre> selectAllGenres() {
        List<Genre> genres = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GENRES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Genre genre = modifyGenre(rs);
                genres.add(genre);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public final void deleteGenre(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GENRES_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateGenre(Genre genre) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GENRES_SQL);) {
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setInt(2, Math.toIntExact(genre.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
