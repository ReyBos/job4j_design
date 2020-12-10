package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getColor() {
        return color;
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
        JSONObject jsonMaterial = new JSONObject("{\"name\": \"cardboard\"}");
        List<String> list = new ArrayList<>();
        list.add("Pens");
        list.add("Pencils");
        JSONArray jsonContent = new JSONArray(list);
        final Box box = new Box(
                false,
                4,
                "Red",
                new Material("cardboard"),
                "Pens", "Pencils"
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isEmpty", box.isEmpty());
        jsonObject.put("capacity", box.getCapacity());
        jsonObject.put("color", box.getColor());
        jsonObject.put("material", jsonMaterial);
        jsonObject.put("content", jsonContent);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(box).toString());
    }

//    public static void main(String[] args) {
//        final Box box = new Box(
//                false,
//                4,
//                "Red",
//                new Material("cardboard"),
//                "Pens", "Pencils"
//        );
//        final Gson gson = new GsonBuilder().create();
//        final String boxJson = gson.toJson(box);
//        System.out.println(boxJson);
//        final Box boxMod = gson.fromJson(boxJson, Box.class);
//        System.out.println(boxMod);
//    }
}
