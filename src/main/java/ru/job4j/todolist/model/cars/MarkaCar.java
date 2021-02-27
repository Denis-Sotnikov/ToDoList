package ru.job4j.todolist.model.cars;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "marka_car")
public class MarkaCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelCar> modelCarList = new ArrayList<>();


    public static MarkaCar of(String name) {
        MarkaCar markaCar = new MarkaCar();
        markaCar.name = name;
        return markaCar;
    }

    public MarkaCar() {
    }

    public MarkaCar(int id) {
        this.id = id;
    }

    public MarkaCar(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addUser(ModelCar modelCar) {
        this.modelCarList.add(modelCar);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelCar> getModelCarList() {
        return modelCarList;
    }

    public void setModelCarList(List<ModelCar> modelCarList) {
        this.modelCarList = modelCarList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarkaCar markaCar = (MarkaCar) o;
        return id == markaCar.id
                && Objects.equals(name, markaCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MarkaCar{"
                + "id=" + id
                + ", name='" + name
                + '\'' + '}';
    }
}
