package ru.job4j.todolist.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todolist.model.Task;
import ru.job4j.todolist.model.User;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class HbmToDoList  implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Collection<Task> findAllTask() {
        return this.tx(
                session -> session.createQuery(
                        "from ru.job4j.todolist.model.Task")
                        .list()
        );
    }

    @Override
    public void save(Task task) {
        this.tx(session -> session.save(task));
    }

    @Override
    public Task findById(int id) {
        return this.tx(session -> session.get(Task.class, id));
    }

    @Override
    public void update(Task task) {
        this.tx(
                session -> {
                    session.update(task);
                    return true;
                }
        );
    }

    @Override
    public void delete(int id) {
        this.tx(
                session -> {
                    Task task = new Task();
                    task.setId(id);
                    session.delete(task);
                    return true;
                }
        );
    }

    @Override
    public User create(User user) {
        this.tx(session -> session.save(user));
        return user;
    }

    @Override
    public User findUserByName(String key) {
        System.out.println("here");
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery(
                "from ru.job4j.todolist.model.User where name = " + "'" + key + "'").list();
        session.getTransaction().commit();
        session.close();
        return (User) result.get(0);
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}