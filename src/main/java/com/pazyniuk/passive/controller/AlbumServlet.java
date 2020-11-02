package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.Album;
import com.pazyniuk.passive.model.DAO.AlbumDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/album", urlPatterns = {"/album/*"})
public class AlbumServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AlbumDAO albumDAO;

    public final void init() {
        albumDAO = new AlbumDAO();
    }

    private Album modifyAlbum(final HttpServletRequest request) {
        String year = request.getParameter("year");
        String name = request.getParameter("name");
        Album album = new Album();
        album.setName(name);
        album.setYear(year);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            album.setId(id);
        } catch (Exception ignored) {

        }
        return album;
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
                case "/album/new":
                    showNewForm(request, response);
                    break;
                case "/album/delete":
                    deleteAlbum(request, response);
                    break;
                case "/album/edit":
                    showEditForm(request, response);
                    break;
                case "/album/update":
                    updateAlbum(request, response);
                    break;
                case "/album/insert":
                    insertAlbum(request, response);
                    break;
                default:
                    listAlbum(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<Album> listAlbum = albumDAO.selectAllAlbums();
        request.setAttribute("listAlbum", listAlbum);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/album-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/album-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Album existingAlbum = albumDAO.selectAlbum(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/album-form.jsp");
        request.setAttribute("album", existingAlbum);
        dispatcher.forward(request, response);
    }

    private void updateAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        Album album = modifyAlbum(request);
        albumDAO.updateAlbum(album);
        response.sendRedirect("list");
    }

    private void insertAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        Album newAlbum = modifyAlbum(request);
        albumDAO.insertAlbum(newAlbum);
        response.sendRedirect("list");
    }

    private void deleteAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        albumDAO.deleteAlbum(id);
        response.sendRedirect("list");
    }
}
