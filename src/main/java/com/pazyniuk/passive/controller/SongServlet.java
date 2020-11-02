package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.Song;
import com.pazyniuk.passive.model.DAO.SongDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/song", urlPatterns = ("/song/*"))
public class SongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SongDAO songDAO;

    public final void init() {
        songDAO = new SongDAO();
    }

    private Song modifySong(final HttpServletRequest request) {
        int genre = Integer.parseInt(request.getParameter("genre"));
        int author = Integer.parseInt(request.getParameter("author"));
        String name = request.getParameter("name");
        int recordLabel = Integer.parseInt(request.getParameter("record_label"));
        float price = Float.parseFloat(request.getParameter("price"));
        int downloadCount = Integer.parseInt(request.getParameter("download_count"));
        int album = Integer.parseInt(request.getParameter("album"));
        Song song = new Song();
        song.setGenre(genre);
        song.setAuthor(author);
        song.setName(name);
        song.setRecordLabel(recordLabel);
        song.setPrice(price);
        song.setDownloadCount(downloadCount);
        song.setAlbum(album);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            song.setId(id);
        } catch (Exception ignored) {

        }
        return song;
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
                case "/song/new":
                    showNewForm(request, response);
                    break;
                case "/song/delete":
                    deleteSong(request, response);
                    break;
                case "/song/edit":
                    showEditForm(request, response);
                    break;
                case "/song/update":
                    updateSong(request, response);
                    break;
                case "/song/insert":
                    insertSong(request, response);
                    break;
                default:
                    listSong(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listSong(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<Song> listSong = songDAO.selectAllSongs();
        request.setAttribute("listSong", listSong);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/song-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/song-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Song existingSong = songDAO.selectSong(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/song-form.jsp");
        request.setAttribute("song", existingSong);
        dispatcher.forward(request, response);
    }

    private void updateSong(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        Song song = modifySong(request);
        songDAO.updateSong(song);
        response.sendRedirect("list");
    }

    private void insertSong(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        Song newSong = modifySong(request);
        songDAO.insertSong(newSong);
        response.sendRedirect("list");
    }

    private void deleteSong(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        songDAO.deleteSong(id);
        response.sendRedirect("list");
    }
}
