package ru.job4j.todolist.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private boolean done;
    private String author;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> listOfCategory = new ArrayList<>();

    public Task() {
        this.created = new Timestamp(new Date().getTime());
    }

    public Task(String description) {
        this.description = description;
        this.created = new Timestamp(new Date().getTime());
        this.done = false;
    }

    public Task(String description, String author) {
        this.description = description;
        this.author = author;
        this.created = new Timestamp(new Date().getTime());
        this.done = false;
    }

    public Task(int id, String description, Timestamp created, boolean done) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Task(String description, String author, List<Category> categories) {
        this.description = description;
        this.author = author;
        this.created = new Timestamp(new Date().getTime());
        this.done = false;
        this.listOfCategory.addAll(categories);
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Category> getListOfCategory() {
        return listOfCategory;
    }

    public void setListOfCategory(List<Category> listOfCategory) {
        this.listOfCategory = listOfCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id
                && done == task.done
                && Objects.equals(description, task.description)
                && Objects.equals(created, task.created)
                && Objects.equals(author, task.author)
                && Objects.equals(listOfCategory, task.listOfCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, done, author, listOfCategory);
    }

    @Override
    public String toString() {
        return "Task{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created=" + created
                + ", done=" + done
                + ", author='" + author + '\''
                + ", listOfCategory=" + listOfCategory
                + '}';
    }
}


