package ru.job4j.todolist.model.book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book one = Book.of("Town");
            Book two = Book.of("Behavior people");
            Book three = Book.of("Animals");
            Book four = Book.of("Earth");
            Book five = Book.of("Just do it");

            Author firstAuthor = Author.of("Jo Smith");
            firstAuthor.getBooks().add(one);
            firstAuthor.getBooks().add(two);
            firstAuthor.getBooks().add(five);

            Author secondAuthor = Author.of("Jules Verne");
            secondAuthor.getBooks().add(two);

            Author threeAuthor = Author.of("Aleksandr Duma");
            threeAuthor.getBooks().add(two);
            threeAuthor.getBooks().add(one);
            threeAuthor.getBooks().add(four);
            threeAuthor.getBooks().add(five);

            session.persist(firstAuthor);
            session.persist(secondAuthor);
            session.persist(threeAuthor);

            Author author = session.get(Author.class, 3);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}