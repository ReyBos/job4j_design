package ru.job4j.ood.isp.item;

import java.util.List;

public class ExitItem implements MenuItem {
    List<MenuItem> children;

    public ExitItem(MenuItem... items) {
        children = List.of(items);
    }

    @Override
    public String getName() {
        return "Выход";
    }

    @Override
    public boolean doAction() {
        return false;
    }

    @Override
    public List<MenuItem> getChildren() {
        return List.copyOf(children);
    }
}