package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Box {
    private boolean isEmpty;
    private int capacity;
    private String color;
    private Material material;
    private String[] content;

    public Box(boolean isEmpty, int capacity, String color, Material material, String... content) {
        this.isEmpty = isEmpty;
        this.capacity = capacity;
        this.color = color;
        this.material = material;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Box{"
                + "isEmpty=" + isEmpty
                + ", capacity=" + capacity
                + ", color='" + color + '\''
                + ", material=" + material
                + ", content=" + Arrays.toString(content)
                + '}';
    }

    public static void main(String[] args) {
        final Box box = new Box(
                false,
                4,
                "Red",
                new Material("cardboard"),
                "Pens", "Pencils"
        );
        final Gson gson = new GsonBuilder().create();
        final String boxJson = gson.toJson(box);
        System.out.println(boxJson);
        final Box boxMod = gson.fromJson(boxJson, Box.class);
        System.out.println(boxMod);
    }
}
