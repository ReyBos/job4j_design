package ru.job4j.ood.ocp;

import java.util.ArrayList;

@SuppressWarnings("checkstyle:WhitespaceAround")
public class OcrViolation {
    //ссылка не абстрактного типа а конкретной реализации
    private Lion lion;

    private interface Animal { }

    private static class Lion implements Animal { }

    // для птицы и самолета можно сделать один интерфейс Flyable
    private static class Bird {
        public String fly() {
            return "Кар-кар";
        }
    }

    private static class Plane {
        public String fly() {
            return "Вжжжжж";
        }
    }

    // Возвращаем реализацию а не абстракцию
    public ArrayList<String> getArrayList() {
        return new ArrayList<>();
    }
}
