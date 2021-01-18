package ru.job4j.food;

import ru.job4j.food.container.Container;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Container> containers;

    public ControlQuality(List<Container> containers) {
        this.containers = containers;
    }

    public void sort(Food item) throws SQLException {
        for (Container container : containers) {
            if (container.accept(item)) {
                container.add(item);
                return;
            }
        }
        throw new IllegalStateException("Нет подходящего хранилища");
    }

    public void resort() throws SQLException {
        List<Food> containersFood = new ArrayList<>();
        for (Container container : containers) {
            containersFood.addAll(container.deleteAll());
        }
        for (Food food : containersFood) {
            sort(food);
        }
    }

    public List<Container> getContainers() {
        return containers;
    }
}
