package ru.job4j.todolist.servlet;

import com.google.gson.Gson;
import ru.job4j.todolist.model.Task;
import ru.job4j.todolist.store.HbmToDoList;
import ru.job4j.todolist.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class TaskServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Store store = new HbmToDoList();
            String author = req.getParameter("author");
            System.out.println("author = " + author);
            resp.setContentType("application/json; charset=windows-1251");
            String description = req.getParameter("description");
            System.out.println("description = " + description);
            store.save(new Task(description, author));
            Collection<Task> list = store.findAllTask();
            String jso = new Gson().toJson(list);
            System.out.println(jso);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(jso);
            writer.flush();
        }
}
