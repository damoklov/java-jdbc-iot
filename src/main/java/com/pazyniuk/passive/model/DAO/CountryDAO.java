package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class CountryDAO {
    private static final String SELECT_COUNTRIES_BY_ID = "SELECT id, name FROM pazyniuk.country WHERE id = ?;";
    private static final String SELECT_ALL_COUNTRIES = "SELECT id, name FROM pazyniuk.country;";
    private static final String DELETE_COUNTRIES_SQL = "DELETE FROM pazyniuk.country WHERE id = ?;";
    private static final String UPDATE_COUNTRIES_SQL = "UPDATE pazyniuk.country SET name = ? WHERE id = ?;";
    private static final String INSERT_COUNTRIES_SQL = "INSERT INTO pazyniuk.country (name) VALUES (?);";

    private Country modifyCountry(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String name = rs.getString("name");

        Country country = new Country();
        country.setId(id);
        country.setName(name);

        return country;
    }

    public final void insertCountry(Country country) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COUNTRIES_SQL)) {
            preparedStatement.setString(1, String.valueOf(country.getName()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final Country selectCountry(final int id) {
        Country country = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRIES_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                country = modifyCountry(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return country;
    }

    public final List<Country> selectAllCountries() {
        List<Country> countries = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Country country = modifyCountry(rs);
                countries.add(country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return countries;
    }

    public final void deleteCountry(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTRIES_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateCountry(Country country) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNTRIES_SQL);) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.setInt(2, Math.toIntExact(country.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
