package com.pazyniuk.passive.controller;

import com.pazyniuk.passive.model.UserSong;
import com.pazyniuk.passive.model.DAO.UserSongDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/usersong", urlPatterns = {"/usersong/*"})
public class UserSongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserSongDAO userSongDAO;

    public final void init() {
        userSongDAO = new UserSongDAO();
    }

    private UserSong modifyUserSong(final HttpServletRequest request) {
        int idUser = Integer.parseInt(request.getParameter("id_user"));
        int idSong = Integer.parseInt(request.getParameter("id_song"));
        UserSong userSong = new UserSong();
        userSong.setIdUser(idUser);
        userSong.setIdSong(idSong);
        try {
            long id = Long.parseLong(request.getParameter("id"));
            userSong.setId(id);
        } catch (Exception ignored) {

        }
        return userSong;
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
                case "/usersong/new":
                    showNewForm(request, response);
                    break;
                case "/usersong/delete":
                    deleteUserSong(request, response);
                    break;
                case "/usersong/edit":
                    showEditForm(request, response);
                    break;
                case "/usersong/update":
                    updateUserSong(request, response);
                    break;
                case "/usersong/insert":
                    insertUserSong(request, response);
                    break;
                default:
                    listUserSong(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUserSong(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        List<UserSong> listUserSong = userSongDAO.selectAllUserSongs();
        request.setAttribute("listUserSong", listUserSong);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/usersong-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/usersong-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getParameterNames());
        int id = Integer.parseInt(request.getParameter("id"));
        UserSong existingUserSong = userSongDAO.selectUserSong(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/usersong-form.jsp");
        request.setAttribute("usersong", existingUserSong);
        dispatcher.forward(request, response);
    }

    private void updateUserSong(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, SQLException {
        UserSong userSong = modifyUserSong(request);
        userSongDAO.updateUserSongs(userSong);
        response.sendRedirect("list");
    }

    private void insertUserSong(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        UserSong newUserSong = modifyUserSong(request);
        userSongDAO.insertUserSong(newUserSong);
        response.sendRedirect("list");
    }

    private void deleteUserSong(final HttpServletRequest request, final HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userSongDAO.deleteUserSongs(id);
        response.sendRedirect("list");
    }
}
