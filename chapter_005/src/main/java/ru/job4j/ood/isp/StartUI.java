package ru.job4j.ood.isp;

import ru.job4j.ood.isp.builder.SimpleMenuBuilder;
import ru.job4j.ood.isp.io.*;
import ru.job4j.ood.isp.item.*;

import java.util.List;

public class StartUI {
    private final Output out;
    private final Input in;

    public StartUI(Output out, Input in) {
        this.out = out;
        this.in = in;
    }

    public void init(List<MenuItem> menuItems) {
        boolean run = true;
        while (run) {
            showMenu(menuItems);
            int select = in.askInt("Select: ");
            if (select < 0 || select >= menuItems.size()) {
                out.println("Неверный ввод, вы можете выбрать: 0 .. " + (menuItems.size() - 1));
                continue;
            }
            run = menuItems.get(select).doAction();
        }
    }

    private void showMenu(List<MenuItem> menuItems) {
        for (int i = 0; i < menuItems.size(); i++) {
            out.println(i + ". " + menuItems.get(i).getName());
        }
    }

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input in = new ValidateInput(new ConsoleInput(), out);
        SimpleMenuBuilder menuBuilder = new SimpleMenuBuilder();
        FirstSubSubItem firstSubSubItem = new FirstSubSubItem(out);
        FirstSubItem firstSubItem = new FirstSubItem(out, firstSubSubItem);
        SecondSubItem secondSubItem = new SecondSubItem(out);
        FirstItem firstItem = new FirstItem(out, firstSubItem, secondSubItem);
        ExitItem exitItem = new ExitItem();
        List<MenuItem> input = List.of(firstItem, exitItem);
        List<MenuItem> menu = menuBuilder.makeMenu(input);
        new StartUI(out, in).init(menu);
    }
}
