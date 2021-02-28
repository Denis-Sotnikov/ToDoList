package ru.job4j.todolist.model.lazyinitialization;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "marka_car_lazy")
public class MarkaCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "markaCar")
    private List<ModelCar> modelCarList = new ArrayList<>();


    public static ru.job4j.todolist.model.lazyinitialization.MarkaCar of(String name) {
        ru.job4j.todolist.model.lazyinitialization.MarkaCar markaCar = new ru.job4j.todolist.model.lazyinitialization.MarkaCar();
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

    public void addCar(ru.job4j.todolist.model.lazyinitialization.ModelCar modelCar) {
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

    public List<ru.job4j.todolist.model.lazyinitialization.ModelCar> getModelCarList() {
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
        ru.job4j.todolist.model.lazyinitialization.MarkaCar markaCar = (ru.job4j.todolist.model.lazyinitialization.MarkaCar) o;
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