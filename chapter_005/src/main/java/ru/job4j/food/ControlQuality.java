package ru.job4j.food;

import ru.job4j.food.storage.Storage;

public class ControlQuality {
    private Storage warehouse;
    private Storage shop;
    private Storage trash;
    private StorageDispatch storageDispatch;

    public ControlQuality(Storage warehouse, Storage shop, Storage trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
        this.storageDispatch = new StorageDispatch();
    }

    public boolean sort(Food item) {
        return storageDispatch.getStorage(item, this).add(item);
    }

    public Storage getWarehouse() {
        return warehouse;
    }

    public Storage getShop() {
        return shop;
    }

    public Storage getTrash() {
        return trash;
    }
}
