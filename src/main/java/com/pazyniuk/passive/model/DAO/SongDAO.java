package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class SongDAO {
    private static final String SELECT_SONGS_BY_ID = "SELECT id, name, price, download_count, genre, author, record_label, album FROM pazyniuk.song WHERE id = ?;";
    private static final String SELECT_ALL_SONGS = "SELECT id, name, price, download_count, genre, author, record_label, album FROM pazyniuk.song;";
    private static final String DELETE_SONGS_SQL = "DELETE FROM pazyniuk.song WHERE id = ?;";
    private static final String UPDATE_SONGS_SQL = "UPDATE pazyniuk.song SET name = ?, price = ?, download_count = ?, genre = ?, author = ?, record_label = ?, album = ? WHERE id = ?;";
    private static final String INSERT_SONGS_SQL = "INSERT INTO pazyniuk.song (genre, author, name, record_label, price, download_count, album) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private Song modifySong(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        int author = rs.getInt("author");
        String name = rs.getString("name");
        float price = rs.getFloat("price");
        int downloadCount = rs.getInt("download_count");
        int genre;
        int recordLabel;
        int album;
        try {
            genre = Integer.parseInt(rs.getString("genre"));
        } catch (java.lang.NumberFormatException e) {
            genre = 0;
        }
        try {
            recordLabel = Integer.parseInt(rs.getString("record_label"));
        } catch (java.lang.NumberFormatException e) {
            recordLabel = 0;
        }
        try {
            album = Integer.parseInt(rs.getString("album"));
        } catch (java.lang.NumberFormatException e) {
            album = 0;
        }
        Song song = new Song();
        song.setId(id);
        song.setGenre(genre);
        song.setAuthor(author);
        song.setName(name);
        song.setRecordLabel(recordLabel);
        song.setPrice(price);
        song.setDownloadCount(downloadCount);
        song.setAlbum(album);

        return song;
    }

    public final void insertSong(Song song) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SONGS_SQL)) {
            preparedStatement.setString(1, String.valueOf(song.getGenre()));
            preparedStatement.setString(2, String.valueOf(song.getAuthor()));
            preparedStatement.setString(3, String.valueOf(song.getName()));
            preparedStatement.setString(4, String.valueOf(song.getRecordLabel()));
            preparedStatement.setString(5, String.valueOf(song.getPrice()));
            preparedStatement.setString(6, String.valueOf(song.getDownloadCount()));
            preparedStatement.setString(7, String.valueOf(song.getAlbum()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final Song selectSong(final int id) {
        Song song = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SONGS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                song = modifySong(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return song;
    }

    public final List<Song> selectAllSongs() {
        List<Song> songs = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SONGS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Song song = modifySong(rs);
                songs.add(song);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public final void deleteSong(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SONGS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateSong(Song song) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SONGS_SQL);) {
            preparedStatement.setString(1, String.valueOf(song.getName()));
            preparedStatement.setString(2, String.valueOf(song.getPrice()));
            preparedStatement.setString(3, String.valueOf(song.getDownloadCount()));
            preparedStatement.setString(4, String.valueOf(song.getGenre()));
            preparedStatement.setString(5, String.valueOf(song.getAuthor()));
            preparedStatement.setString(6, String.valueOf(song.getRecordLabel()));
            preparedStatement.setString(7, String.valueOf(song.getAlbum()));
            preparedStatement.setInt(8, Math.toIntExact(song.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
