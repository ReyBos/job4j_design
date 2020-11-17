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
        BiConsumer<Integer, T> func = mem::set;
        return changeMem(id, model, func);
    }

    @Override
    public boolean delete(String id) {
        BiConsumer<Integer, T> func = (index, model) -> mem.remove(index.intValue());
        return changeMem(id, null, func);
    }

    private boolean changeMem(String id, T newModel, BiConsumer<Integer, T> func) {
        int index = getModelIndex(id);
        boolean rsl = false;
        if (index != -1) {
            rsl = true;
            func.accept(index, newModel);
        }
        return rsl;
    }

    private int getModelIndex(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
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