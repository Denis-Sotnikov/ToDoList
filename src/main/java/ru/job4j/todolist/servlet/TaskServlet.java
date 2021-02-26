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
            resp.setContentType("application/json; charset=windows-1251");
            String description = req.getParameter("description");
            store.save(new Task(description));
            Collection<Task> list = store.findAllTask();
            String jso = new Gson().toJson(list);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(jso);
            writer.flush();
            Task task = store.findById(6);
            System.out.println("%%%%%%%%%%%%%%%%%");
            System.out.println(task.getDescription());
            store.update(task);
        }
}
