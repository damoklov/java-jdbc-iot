package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.ItunesUser;
import com.pazyniuk.passive.model.DAO.ItunesUserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/itunesuser", urlPatterns = ("/itunesuser/*"))
public class ItunesUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItunesUserDAO itunesUserDAO;

    public final void init() {
        itunesUserDAO = new ItunesUserDAO();
    }

    private ItunesUser modifySong(final HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String joinedDate = request.getParameter("joined_date");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int gender = Integer.parseInt(request.getParameter("gender"));
        int creditCard = Integer.parseInt(request.getParameter("credit_card"));

        ItunesUser itunesUser = new ItunesUser();
        itunesUser.setUsername(username);
        itunesUser.setPassword(password);
        itunesUser.setJoinedDate(joinedDate);
        itunesUser.setName(name);
        itunesUser.setSurname(surname);
        itunesUser.setGender(gender);
        itunesUser.setCreditCard(creditCard);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            itunesUser.setId(id);
        } catch (Exception ignored) {

        }
        return itunesUser;
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
                case "/itunesuser/new":
                    showNewForm(request, response);
                    break;
                case "/itunesuser/delete":
                    deleteItunesUser(request, response);
                    break;
                case "/itunesuser/edit":
                    showEditForm(request, response);
                    break;
                case "/itunesuser/update":
                    updateItunesUser(request, response);
                    break;
                case "/itunesuser/insert":
                    insertItunesUser(request, response);
                    break;
                default:
                    listItunesUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listItunesUser(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<ItunesUser> listItunesUser = itunesUserDAO.selectAllItunesUsers();
        request.setAttribute("listItunesUser", listItunesUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/itunesuser-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/itunesuser-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ItunesUser existingItunesUser = itunesUserDAO.selectItunesUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/itunesuser-form.jsp");
        request.setAttribute("itunesuser", existingItunesUser);
        dispatcher.forward(request, response);
    }

    private void updateItunesUser(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        ItunesUser itunesUser = modifySong(request);
        itunesUserDAO.updateItunesUser(itunesUser);
        response.sendRedirect("list");
    }

    private void insertItunesUser(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        ItunesUser newItunesUser = modifySong(request);
        itunesUserDAO.insertItunesUser(newItunesUser);
        response.sendRedirect("list");
    }

    private void deleteItunesUser(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        itunesUserDAO.deleteItunesUser(id);
        response.sendRedirect("list");
    }
}
