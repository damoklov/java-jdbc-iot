package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.Artist;
import com.pazyniuk.passive.model.DAO.ArtistDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/artist", urlPatterns = {"/artist/*"})
public class ArtistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArtistDAO artistDAO;

    public final void init() {
        artistDAO = new ArtistDAO();
    }

    private Artist modifyArtist(final HttpServletRequest request) {
        String name = request.getParameter("name");
        int country = Integer.parseInt(request.getParameter("country"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        Artist artist = new Artist();
        artist.setName(name);
        artist.setCountry(country);
        artist.setGender(gender);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            artist.setId(id);
        } catch (Exception ignored) {

        }
        return artist;
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
                case "/artist/new":
                    showNewForm(request, response);
                    break;
                case "/artist/delete":
                    deleteArtist(request, response);
                    break;
                case "/artist/edit":
                    showEditForm(request, response);
                    break;
                case "/artist/update":
                    updateArtist(request, response);
                    break;
                case "/artist/insert":
                    insertAlbum(request, response);
                    break;
                default:
                    listArtist(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listArtist(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<Artist> listArtist = artistDAO.selectAllArtists();
        request.setAttribute("listArtist", listArtist);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/artist-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/artist-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Artist existingArtist = artistDAO.selectArtist(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/artist-form.jsp");
        request.setAttribute("artist", existingArtist);
        dispatcher.forward(request, response);
    }

    private void updateArtist(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        Artist artist = modifyArtist(request);
        artistDAO.updateArtist(artist);
        response.sendRedirect("list");
    }

    private void insertAlbum(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        Artist newArtist = modifyArtist(request);
        artistDAO.insertArtist(newArtist);
        response.sendRedirect("list");
    }

    private void deleteArtist(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        artistDAO.deleteArtist(id);
        response.sendRedirect("list");
    }
}
