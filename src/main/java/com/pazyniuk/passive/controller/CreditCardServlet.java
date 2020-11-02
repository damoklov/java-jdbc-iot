package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.CreditCard;
import com.pazyniuk.passive.model.DAO.CreditCardDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/creditcard", urlPatterns = {"/creditcard/*"})
public class CreditCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CreditCardDAO creditCardDAO;

    public final void init() {
        creditCardDAO = new CreditCardDAO();
    }

    private CreditCard modifyCreditCard(final HttpServletRequest request) {
        String number = request.getParameter("number");
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(number);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            creditCard.setId(id);
        } catch (Exception ignored) {

        }
        return creditCard;
    }

    protected final void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

    protected final void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        String action;
        if (request.getPathInfo() == null) {
            action = request.getServletPath();
        } else {
            action = request.getServletPath() + request.getPathInfo();
        }
        try {
            switch (action) {
                case "/creditcard/new":
                    showNewForm(request, response);
                    break;
                case "/creditcard/delete":
                    deleteCreditCard(request, response);
                    break;
                case "/creditcard/edit":
                    showEditForm(request, response);
                    break;
                case "/creditcard/update":
                    updateCreditCard(request, response);
                    break;
                case "/creditcard/insert":
                    insertCreditCard(request, response);
                    break;
                default:
                    listCreditCard(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCreditCard(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<CreditCard> listCreditCard = creditCardDAO.selectAllCreditCards();
        request.setAttribute("listCreditCard", listCreditCard);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/creditcard-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/creditcard-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CreditCard existingCreditCard = creditCardDAO.selectCreditCard(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/creditcard-form.jsp");
        request.setAttribute("creditcard", existingCreditCard);
        dispatcher.forward(request, response);
    }

    private void updateCreditCard(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        CreditCard creditCard = modifyCreditCard(request);
        creditCardDAO.updateCreditCard(creditCard);
        response.sendRedirect("list");
    }

    private void insertCreditCard(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        CreditCard newCreditCard = modifyCreditCard(request);
        creditCardDAO.insertCreditCard(newCreditCard);
        response.sendRedirect("list");
    }

    private void deleteCreditCard(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        creditCardDAO.deleteCreditCard(id);
        response.sendRedirect("list");
    }
}
