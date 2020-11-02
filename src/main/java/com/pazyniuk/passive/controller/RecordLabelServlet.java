package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.RecordLabel;
import com.pazyniuk.passive.model.DAO.RecordLabelDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/recordlabel", urlPatterns = {"/recordlabel/*"})
public class RecordLabelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RecordLabelDAO recordLabelDAO;

    public final void init() {
        recordLabelDAO = new RecordLabelDAO();
    }

    private RecordLabel modifyRecordLabel(final HttpServletRequest request) {
        String yearEstablished = request.getParameter("year_established");
        String title = request.getParameter("title");
        RecordLabel recordLabel = new RecordLabel();
        recordLabel.setTitle(title);
        recordLabel.setYearEstablished(yearEstablished);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            recordLabel.setId(id);
        } catch (Exception ignored) {

        }
        return recordLabel;
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
                case "/recordlabel/new":
                    showNewForm(request, response);
                    break;
                case "/recordlabel/delete":
                    deleteRecordLabel(request, response);
                    break;
                case "/recordlabel/edit":
                    showEditForm(request, response);
                    break;
                case "/recordlabel/update":
                    updateRecordLabel(request, response);
                    break;
                case "/recordlabel/insert":
                    insertRecordLabel(request, response);
                    break;
                default:
                    listRecordLabel(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listRecordLabel(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<RecordLabel> listRecordLabel = recordLabelDAO.selectAllRecordLabels();
        request.setAttribute("listRecordLabel", listRecordLabel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/recordlabel-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/recordlabel-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RecordLabel existingRecordLabel = recordLabelDAO.selectRecordLabel(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/recordlabel-form.jsp");
        request.setAttribute("recordlabel", existingRecordLabel);
        dispatcher.forward(request, response);
    }

    private void updateRecordLabel(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        RecordLabel recordLabel = modifyRecordLabel(request);
        recordLabelDAO.updateRecordLabel(recordLabel);
        response.sendRedirect("list");
    }

    private void insertRecordLabel(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        RecordLabel newRecordLabel = modifyRecordLabel(request);
        recordLabelDAO.insertRecordLabel(newRecordLabel);
        response.sendRedirect("list");
    }

    private void deleteRecordLabel(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        recordLabelDAO.deleteRecordLabel(id);
        response.sendRedirect("list");
    }
}
