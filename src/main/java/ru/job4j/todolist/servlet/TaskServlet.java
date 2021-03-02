package ru.job4j.todolist.servlet;

import com.google.gson.Gson;
import ru.job4j.todolist.model.Category;
import ru.job4j.todolist.model.Task;
import ru.job4j.todolist.store.HbmToDoList;
import ru.job4j.todolist.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TaskServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Category> categories = HbmToDoList.instanceOf().allCategories();
                Store store = HbmToDoList.instanceOf();
                resp.setContentType("application/json; charset=windows-1251");
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/mainpage.jsp").forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Store store = HbmToDoList.instanceOf();
        String[] cIds = req.getParameterValues("cIds");
        String str = cIds[0].replaceAll("\\D", "");
        char[] strToArray = str.toCharArray();
        resp.setContentType("application/json; charset=windows-1251");
        List<Category> categories = HbmToDoList.instanceOf().allCategories();
        List<Category> categoriesList = new ArrayList<>();
        for (char s : strToArray) {
            categoriesList.add(categories.get(Integer.parseInt(String.valueOf(s)) - 1));
        }
        store.save(new Task(
                req.getParameter("description"),
                req.getParameter("author"),
                categoriesList));
        Collection<Task> list = store.findAllTask();
        String jso = new Gson().toJson(list);
        System.out.println(jso);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(jso);
        writer.flush();
    }
}
