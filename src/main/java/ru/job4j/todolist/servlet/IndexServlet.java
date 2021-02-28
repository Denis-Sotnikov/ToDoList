package ru.job4j.todolist.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        System.out.println("sc.getAttribute(\"user\") = " + sc.getAttribute("user"));
        if (sc.getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/mainpage.jsp");
        } else {
            req.getRequestDispatcher("autorization.jsp").forward(req, resp);
        }
    }
}