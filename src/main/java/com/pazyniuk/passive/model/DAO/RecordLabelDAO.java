package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.RecordLabel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class RecordLabelDAO {
    private static final String SELECT_RECORD_LABELS_BY_ID = "SELECT id, title, year_established FROM pazyniuk.record_label WHERE id = ?;";
    private static final String SELECT_ALL_RECORD_LABELS = "SELECT id, title, year_established FROM pazyniuk.record_label;";
    private static final String DELETE_RECORD_LABELS_SQL = "DELETE FROM pazyniuk.record_label WHERE id = ?;";
    private static final String UPDATE_RECORD_LABELS_SQL = "UPDATE pazyniuk.record_label SET title = ?, year_established = ? WHERE id = ?;";
    private static final String INSERT_RECORD_LABELS_SQL = "INSERT INTO pazyniuk.record_label (title, year_established) VALUES (?, ?);";

    private RecordLabel modifyRecordLabel(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String title = rs.getString("title");
        String yearEstablished = rs.getString("year_established");

        RecordLabel recordLabel = new RecordLabel();
        recordLabel.setId(id);
        recordLabel.setTitle(title);
        recordLabel.setYearEstablished(yearEstablished);

        return recordLabel;
    }

    public final void insertRecordLabel(RecordLabel recordLabel) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD_LABELS_SQL)) {
            preparedStatement.setString(1, String.valueOf(recordLabel.getTitle()));
            preparedStatement.setString(2, String.valueOf(recordLabel.getYearEstablished()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final RecordLabel selectRecordLabel(final int id) {
        RecordLabel recordLabel = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECORD_LABELS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                recordLabel = modifyRecordLabel(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return recordLabel;
    }

    public final List<RecordLabel> selectAllRecordLabels() {
        List<RecordLabel> recordLabels = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RECORD_LABELS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                RecordLabel recordLabel = modifyRecordLabel(rs);
                recordLabels.add(recordLabel);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return recordLabels;
    }

    public final void deleteRecordLabel(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECORD_LABELS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateRecordLabel(RecordLabel recordLabel) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD_LABELS_SQL);) {
            preparedStatement.setString(1, recordLabel.getTitle());
            preparedStatement.setString(2, recordLabel.getYearEstablished());
            preparedStatement.setInt(3, Math.toIntExact(recordLabel.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
