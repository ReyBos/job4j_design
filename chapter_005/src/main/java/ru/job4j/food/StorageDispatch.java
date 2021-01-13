package ru.job4j.food;

import ru.job4j.food.storage.Storage;

import java.util.LinkedHashMap;
import java.util.function.Function;

public class StorageDispatch {
    private final LinkedHashMap<Function<Food, Boolean>, Function<ControlQuality, Storage>> dispatchFood;

    public StorageDispatch() {
        dispatchFood = new LinkedHashMap<>();
        dispatchFood.put(
                food -> Double.compare(food.getLivedTimePercent(), 0.25) < 0,
                ControlQuality::getWarehouse
        );
        dispatchFood.put(
                food -> Double.compare(food.getLivedTimePercent(), 1) < 0,
                ControlQuality::getShop
        );
        dispatchFood.put(
                food -> Double.compare(food.getLivedTimePercent(), 1) > 0,
                ControlQuality::getTrash
        );
    }

    public Storage getStorage(Food item, ControlQuality controlQuality) {
        for (Function<Food, Boolean> predict : dispatchFood.keySet()) {
            if (predict.apply(item)) {
                return dispatchFood.get(predict).apply(controlQuality);
            }
        }
        throw new IllegalStateException("Нет подходящего хранилища");
    }
}
