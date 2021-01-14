package ru.job4j.food;

import ru.job4j.food.storage.Storage;

import java.util.List;

public class ControlQuality {
    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public boolean sort(Food item) {
        for (Storage storage : storages) {
            if (storage.accept(item)) {
                return storage.add(item);
            }
        }
        throw new IllegalStateException("Нет подходящего хранилища");
    }

    public List<Storage> getStorages() {
        return storages;
    }
}
