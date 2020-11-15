package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        BiConsumer<T, T> func = (curModel, newModel) -> {
            int index = mem.indexOf(curModel);
            mem.set(index, newModel);
        };
        return changeMem(id, model, func);
    }

    @Override
    public boolean delete(String id) {
        BiConsumer<T, T> func = (curModel, newModel) -> {
            mem.remove(curModel);
        };
        return changeMem(id, null, func);
    }

    private boolean changeMem(String id, T newModel, BiConsumer<T, T> func) {
        T currModel = findById(id);
        boolean rsl = false;
        if (currModel != null) {
            func.accept(currModel, newModel);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        for (T elem : mem) {
            if (elem.getId().equals(id)) {
                rsl = elem;
                break;
            }
        }
        return rsl;
    }
}