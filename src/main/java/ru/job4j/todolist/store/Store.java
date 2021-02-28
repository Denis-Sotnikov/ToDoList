package ru.job4j.todolist.store;

import ru.job4j.todolist.model.Task;
import ru.job4j.todolist.model.User;

import java.util.Collection;

public interface Store {

    Collection<Task> findAllTask();

    void save(Task task);

    Task findById(int id);

    void update(Task task);

    void delete(int id);

    User create(User user);

    User findUserByName(String name);
}
