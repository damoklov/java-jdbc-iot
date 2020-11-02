package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.Gender;
import com.pazyniuk.passive.model.DAO.GenderDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/gender", urlPatterns = {"/gender/*"})
public class GenderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GenderDAO genderDAO;

    public final void init() {
        genderDAO = new GenderDAO();
    }

    private Gender modifyGender(final HttpServletRequest request) {
        String genderType = request.getParameter("gender_type");
        Gender gender = new Gender();
        gender.setGenderType(genderType);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            gender.setId(id);
        } catch (Exception ignored) {

        }
        return gender;
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
                case "/gender/new":
                    showNewForm(request, response);
                    break;
                case "/gender/delete":
                    deleteGender(request, response);
                    break;
                case "/gender/edit":
                    showEditForm(request, response);
                    break;
                case "/gender/update":
                    updateGender(request, response);
                    break;
                case "/gender/insert":
                    insertGender(request, response);
                    break;
                default:
                    listGender(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listGender(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<Gender> listGender = genderDAO.selectAllGenders();
        request.setAttribute("listGender", listGender);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/gender-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/gender-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Gender existingGender = genderDAO.selectGender(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/gender-form.jsp");
        request.setAttribute("gender", existingGender);
        dispatcher.forward(request, response);
    }

    private void updateGender(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        Gender gender = modifyGender(request);
        genderDAO.updateGender(gender);
        response.sendRedirect("list");
    }

    private void insertGender(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        Gender newGender = modifyGender(request);
        genderDAO.insertGender(newGender);
        response.sendRedirect("list");
    }

    private void deleteGender(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        genderDAO.deleteGender(id);
        response.sendRedirect("list");
    }
}
