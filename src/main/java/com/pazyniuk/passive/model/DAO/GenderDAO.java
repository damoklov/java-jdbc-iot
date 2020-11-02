package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class GenderDAO {
    private static final String SELECT_GENDERS_BY_ID = "SELECT id, gender_type FROM pazyniuk.gender WHERE id = ?;";
    private static final String SELECT_ALL_GENDERS = "SELECT id, gender_type FROM pazyniuk.gender;";
    private static final String DELETE_GENDERS_SQL = "DELETE FROM pazyniuk.gender WHERE id = ?;";
    private static final String UPDATE_GENDERS_SQL = "UPDATE pazyniuk.gender SET gender_type = ? WHERE id = ?;";
    private static final String INSERT_GENDERS_SQL = "INSERT INTO pazyniuk.gender (gender_type) VALUES (?);";

    private Gender modifyGender(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String genderType = rs.getString("gender_type");

        Gender gender = new Gender();
        gender.setId(id);
        gender.setGenderType(genderType);

        return gender;
    }

    public final void insertGender(Gender gender) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GENDERS_SQL)) {
            preparedStatement.setString(1, String.valueOf(gender.getGenderType()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final Gender selectGender(final int id) {
        Gender gender = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENDERS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                gender = modifyGender(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gender;
    }

    public final List<Gender> selectAllGenders() {
        List<Gender> genders = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GENDERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Gender gender = modifyGender(rs);
                genders.add(gender);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return genders;
    }

    public final void deleteGender(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GENDERS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateGender(Gender gender) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GENDERS_SQL);) {
            preparedStatement.setString(1, gender.getGenderType());
            preparedStatement.setInt(2, Math.toIntExact(gender.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
