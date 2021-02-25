package ru.job4j.todolist.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todolist.model.Task;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.List;

public class HbmToDoList  implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Collection<Task> findAllTask() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.todolist.model.Task").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void save(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Task findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Task result = session.get(Task.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void update(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Task task = new Task();
        task.setId(id);
        session.delete(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}