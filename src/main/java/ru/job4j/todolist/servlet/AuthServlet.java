package ru.job4j.todolist.servlet;

import ru.job4j.todolist.model.User;
import ru.job4j.todolist.store.HbmToDoList;
import ru.job4j.todolist.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = new HbmToDoList();
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = store.findUserByName(name);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.do");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("autorization.jsp").forward(req, resp);
        }
    }
}