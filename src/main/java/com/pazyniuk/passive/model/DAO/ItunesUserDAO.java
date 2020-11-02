package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.ItunesUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class ItunesUserDAO {
    private static final String SELECT_USERS_BY_ID = "SELECT id, username, password, joined_date, name, surname, gender, credit_card FROM pazyniuk.itunes_user WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT id, username, password, joined_date, name, surname, gender, credit_card FROM pazyniuk.itunes_user;";
    private static final String DELETE_USERS_SQL = "DELETE FROM pazyniuk.itunes_user WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE pazyniuk.itunes_user SET username = ?, password = ?, joined_date = ?, name = ?, surname = ?, gender = ?, credit_card = ? WHERE id = ?;";
    private static final String INSERT_USERS_SQL = "INSERT INTO pazyniuk.itunes_user (username, password, joined_date, name, surname, gender, credit_card) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private ItunesUser modifyItunesUser(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String joinedDate = rs.getString("joined_date");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        int creditCard;
        int gender;
        try {
            gender = Integer.parseInt(rs.getString("gender"));
        } catch (java.lang.NumberFormatException e) {
            gender = 0;
        }
        try {
            creditCard = Integer.parseInt(rs.getString("credit_card"));
        } catch (java.lang.NumberFormatException e) {
            creditCard = 0;
        }
        ItunesUser itunesUser = new ItunesUser();
        itunesUser.setId(id);
        itunesUser.setUsername(username);
        itunesUser.setPassword(password);
        itunesUser.setJoinedDate(joinedDate);
        itunesUser.setName(name);
        itunesUser.setSurname(surname);
        itunesUser.setGender(gender);
        itunesUser.setCreditCard(creditCard);

        return itunesUser;
    }

    public final void insertItunesUser(ItunesUser itunesUser) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, String.valueOf(itunesUser.getUsername()));
            preparedStatement.setString(2, String.valueOf(itunesUser.getPassword()));
            preparedStatement.setString(3, String.valueOf(itunesUser.getJoinedDate()));
            preparedStatement.setString(4, String.valueOf(itunesUser.getName()));
            preparedStatement.setString(5, String.valueOf(itunesUser.getSurname()));
            preparedStatement.setString(6, String.valueOf(itunesUser.getGender()));
            preparedStatement.setString(7, String.valueOf(itunesUser.getCreditCard()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final ItunesUser selectItunesUser(final int id) {
        ItunesUser itunesUser = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                itunesUser = modifyItunesUser(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itunesUser;
    }

    public final List<ItunesUser> selectAllItunesUsers() {
        List<ItunesUser> itunesUsers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ItunesUser itunesUser = modifyItunesUser(rs);
                itunesUsers.add(itunesUser);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itunesUsers;
    }

    public final void deleteItunesUser(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateItunesUser(ItunesUser itunesUser) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            preparedStatement.setString(1, String.valueOf(itunesUser.getUsername()));
            preparedStatement.setString(2, String.valueOf(itunesUser.getPassword()));
            preparedStatement.setString(3, String.valueOf(itunesUser.getJoinedDate()));
            preparedStatement.setString(4, String.valueOf(itunesUser.getName()));
            preparedStatement.setString(5, String.valueOf(itunesUser.getSurname()));
            preparedStatement.setString(6, String.valueOf(itunesUser.getGender()));
            preparedStatement.setString(7, String.valueOf(itunesUser.getCreditCard()));
            preparedStatement.setInt(8, Math.toIntExact(itunesUser.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
