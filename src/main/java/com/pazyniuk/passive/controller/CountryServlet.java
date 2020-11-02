package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.Country;
import com.pazyniuk.passive.model.DAO.CountryDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/country", urlPatterns = {"/country/*"})
public class CountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CountryDAO countryDAO;

    public final void init() {
        countryDAO = new CountryDAO();
    }

    private Country modifyCountry(final HttpServletRequest request) {
        String name = request.getParameter("name");
        Country country = new Country();
        country.setName(name);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            country.setId(id);
        } catch (Exception ignored) {

        }
        return country;
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
                case "/country/new":
                    showNewForm(request, response);
                    break;
                case "/country/delete":
                    deleteAlbum(request, response);
                    break;
                case "/country/edit":
                    showEditForm(request, response);
                    break;
                case "/country/update":
                    updateAlbum(request, response);
                    break;
                case "/country/insert":
                    insertAlbum(request, response);
                    break;
                default:
                    listCountry(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCountry(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<Country> listCountry = countryDAO.selectAllCountries();
        request.setAttribute("listCountry", listCountry);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/country-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/country-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Country existingAlbum = countryDAO.selectCountry(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/country-form.jsp");
        request.setAttribute("country", existingAlbum);
        dispatcher.forward(request, response);
    }

    private void updateAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        Country country = modifyCountry(request);
        countryDAO.updateCountry(country);
        response.sendRedirect("list");
    }

    private void insertAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        Country newCountry = modifyCountry(request);
        countryDAO.insertCountry(newCountry);
        response.sendRedirect("list");
    }

    private void deleteAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        countryDAO.deleteCountry(id);
        response.sendRedirect("list");
    }
}
