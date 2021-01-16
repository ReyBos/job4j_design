package ru.job4j.ood.isp.item;

import ru.job4j.ood.isp.io.Output;

import java.util.List;

public class FirstSubSubItem implements MenuItem {
    List<MenuItem> children;
    private Output out;

    public FirstSubSubItem(Output out, MenuItem... items) {
        this.out = out;
        children = List.of(items);
    }

    @Override
    public String getName() {
        return "Первый пункт";
    }

    @Override
    public boolean doAction() {
        out.println(getName());
        return true;
    }

    @Override
    public List<MenuItem> getChildren() {
        return List.copyOf(children);
    }
}