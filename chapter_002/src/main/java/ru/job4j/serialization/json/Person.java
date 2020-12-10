package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

//    public static void main(String[] args) {
//        final Person person = new Person(
//                false,
//                30,
//                new Contact("11-111"),
//                "Worker", "Married"
//        );
//        /* Преобразуем объект person в json-строку. */
//        final Gson gson = new GsonBuilder().create();
//        System.out.println(gson.toJson(person));
//        /* Модифицируем json-строку */
//        final String personJson =
//                "{"
//                        + "\"sex\":false,"
//                        + "\"age\":35,"
//                        + "\"contact\":"
//                            + "{"
//                                + "\"phone\":\"+7(924)111-111-11-11\""
//                            + "},"
//                        + "\"statuses\":"
//                        + "[\"Student\",\"Free\"]"
//                + "}";
//        final Person personMod = gson.fromJson(personJson, Person.class);
//        System.out.println(personMod);
//    }

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);
        /* JSONObject напрямую методом put */
        final Person person = new Person(
                false,
                30,
                new Contact("11-111"),
                "Worker", "Married"
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(person).toString());
    }
}