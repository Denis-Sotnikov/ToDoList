package ru.job4j.todolist.store;

import ru.job4j.todolist.model.Task;

import java.util.Collection;

public interface Store {

    Collection<Task> findAllTask();

    void save(Task task);

    Task findById(int id);

    void update(Task task);

    void delete(int id);
}
