package ru.job4j.todolist.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "item")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Timestamp created;
    private boolean done;

    public Task() {
        this.created = new Timestamp(new Date().getTime());
    }

    public Task(String description) {
        this.description = description;
        this.created = new Timestamp(new Date().getTime());
        this.done = false;
    }

    public Task(int id, String description, Timestamp created, boolean done) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", done=" + done +
                '}';
    }
}
