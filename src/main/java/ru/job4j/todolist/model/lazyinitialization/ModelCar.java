package ru.job4j.todolist.model.lazyinitialization;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "model_car_lazy")
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "marka_id")
    private MarkaCar markaCar;

    public static ru.job4j.todolist.model.lazyinitialization.ModelCar of(String name) {
        ru.job4j.todolist.model.lazyinitialization.ModelCar model = new ru.job4j.todolist.model.lazyinitialization.ModelCar();
        model.name = name;
        return model;
    }

    public ModelCar() {
    }

    public ModelCar(int id) {
        this.id = id;
    }

    public ModelCar(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ru.job4j.todolist.model.lazyinitialization.ModelCar modelCar = (ru.job4j.todolist.model.lazyinitialization.ModelCar) o;
        return id == modelCar.id
                && Objects.equals(name, modelCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ModelCar{"
                + "id=" + id
                + ", name='" + name
                + '\''
                + ", markaCar="
                + markaCar
                + '}';
    }
}
