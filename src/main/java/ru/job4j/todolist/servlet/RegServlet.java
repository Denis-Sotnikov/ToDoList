package ru.job4j.todolist.servlet;

import ru.job4j.todolist.model.Role;
import ru.job4j.todolist.model.User;
import ru.job4j.todolist.store.HbmToDoList;
import ru.job4j.todolist.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("posts", PsqlStore.instOf().findAllPosts());
//        req.setAttribute("user", req.getSession().getAttribute("user"));
//        req.getRequestDispatcher("post/posts.jsp").forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Role role = new Role();
        role.setName("user");
        role.setId(1);
        Store store = new HbmToDoList();
        store.create(User.of(req.getParameter("name"),  req.getParameter("password"), role));
        resp.sendRedirect(req.getContextPath() + "/index.do");
    }
}
