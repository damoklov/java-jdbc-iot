package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.Genre;
import com.pazyniuk.passive.model.DAO.GenreDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/genre", urlPatterns = {"/genre/*"})
public class GenreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GenreDAO genreDAO;

    public final void init() {
        genreDAO = new GenreDAO();
    }

    private Genre modifyGenre(final HttpServletRequest request) {
        String name = request.getParameter("name");
        Genre genre = new Genre();
        genre.setName(name);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            genre.setId(id);
        } catch (Exception ignored) {

        }
        return genre;
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
                case "/genre/new":
                    showNewForm(request, response);
                    break;
                case "/genre/delete":
                    deleteAlbum(request, response);
                    break;
                case "/genre/edit":
                    showEditForm(request, response);
                    break;
                case "/genre/update":
                    updateAlbum(request, response);
                    break;
                case "/genre/insert":
                    insertAlbum(request, response);
                    break;
                default:
                    listGenre(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listGenre(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<Genre> listGenre = genreDAO.selectAllGenres();
        request.setAttribute("listGenre", listGenre);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/genre-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/genre-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Genre existingGenre = genreDAO.selectGenre(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/genre-form.jsp");
        request.setAttribute("genre", existingGenre);
        dispatcher.forward(request, response);
    }

    private void updateAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        Genre genre = modifyGenre(request);
        genreDAO.updateGenre(genre);
        response.sendRedirect("list");
    }

    private void insertAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        Genre newGenre = modifyGenre(request);
        genreDAO.insertGenre(newGenre);
        response.sendRedirect("list");
    }

    private void deleteAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        genreDAO.deleteGenre(id);
        response.sendRedirect("list");
    }
}
