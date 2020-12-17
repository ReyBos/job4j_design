package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrepareStatementDemo {
    private Connection connection;

    public PrepareStatementDemo() throws Exception {
        initConnection();
    }

    public void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    public City insert(City city) {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)",
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean update(City city) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement =
                     connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        PrepareStatementDemo prepareStatementDemo = new PrepareStatementDemo();
        City city = new City(0, "Москва", 100);
        City city2 = new City(0, "Краснодар", 100);
        prepareStatementDemo.insert(city);
        prepareStatementDemo.insert(city2);
        city2.setName("test");
        prepareStatementDemo.update(city2);
        List<City> cities = prepareStatementDemo.findAll();
        for (City city1 : cities) {
            System.out.println(city1.toString());
        }
        System.out.println(city.getId());
        System.out.println(prepareStatementDemo.delete(city.getId()));
        System.out.println(prepareStatementDemo.delete(city2.getId()));
        cities = prepareStatementDemo.findAll();
        for (City city3 : cities) {
            System.out.println(city3);
        }
    }
}