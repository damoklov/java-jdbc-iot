package com.pazyniuk.passive.model.DAO;

import com.pazyniuk.passive.model.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.pazyniuk.passive.model.BusinessLogic.getConnection;
import static com.pazyniuk.passive.model.BusinessLogic.printSQLException;

public class CreditCardDAO {
    private static final String SELECT_CREDIT_CARDS_BY_ID = "SELECT id, number FROM pazyniuk.credit_card WHERE id = ?;";
    private static final String SELECT_ALL_CREDIT_CARDS = "SELECT id, number FROM pazyniuk.credit_card;";
    private static final String DELETE_CREDIT_CARDS_SQL = "DELETE FROM pazyniuk.credit_card WHERE id = ?;";
    private static final String UPDATE_CREDIT_CARDS_SQL = "UPDATE pazyniuk.credit_card SET number = ? WHERE id = ?;";
    private static final String INSERT_CREDIT_CARDS_SQL = "INSERT INTO pazyniuk.credit_card (number) VALUES (?);";

    private CreditCard modifyCreditCard(final ResultSet rs) throws SQLException {
        long id = rs.getInt("id");
        String number = rs.getString("number");

        CreditCard creditCard = new CreditCard();
        creditCard.setId(id);
        creditCard.setNumber(number);

        return creditCard;
    }

    public final void insertCreditCard(CreditCard creditCard) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CREDIT_CARDS_SQL)) {
            preparedStatement.setString(1, String.valueOf(creditCard.getNumber()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        }
        catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final CreditCard selectCreditCard(final int id) {
        CreditCard creditCard = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CREDIT_CARDS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                creditCard = modifyCreditCard(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return creditCard;
    }

    public final List<CreditCard> selectAllCreditCards() {
        List<CreditCard> creditCards = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CREDIT_CARDS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CreditCard creditCard = modifyCreditCard(rs);
                creditCards.add(creditCard);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return creditCards;
    }

    public final void deleteCreditCard(final int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CREDIT_CARDS_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void updateCreditCard(CreditCard creditCard) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CREDIT_CARDS_SQL);) {
            preparedStatement.setString(1, creditCard.getNumber());
            preparedStatement.setInt(2, Math.toIntExact(creditCard.getId()));
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
