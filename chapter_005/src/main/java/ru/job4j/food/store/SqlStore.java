package ru.job4j.food.store;

import ru.job4j.food.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SqlStore implements Store {
    private final Connection cn;

    public SqlStore(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void add(Food item) throws SQLException {
        String sql = "insert into food(name, expiryDate, createDate, price, discount) values(?, ?, ?, ?, ?)";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, new Timestamp(item.getExpiryDate().getTime().getTime()));
            statement.setTimestamp(3, new Timestamp(item.getCreateDate().getTime().getTime()));
            statement.setDouble(4, item.getPrice());
            statement.setDouble(5, item.getDiscount());
            statement.execute();
        }
    }

    @Override
    public List<Food> findAll() throws SQLException {
        List<Food> rsl = new ArrayList<>();
        String sql = "select * from food";
       try (PreparedStatement statement = cn.prepareStatement(sql)) {
           try (ResultSet resultSet = statement.executeQuery()) {
               while (resultSet.next()) {
                   Calendar expiryDate = new GregorianCalendar();
                   expiryDate.setTimeInMillis(resultSet.getTimestamp("expiryDate").getTime());
                   Calendar createDate = new GregorianCalendar();
                   createDate.setTimeInMillis(resultSet.getTimestamp("createDate").getTime());
                   rsl.add(new Food(
                           resultSet.getString("name"),
                           expiryDate,
                           createDate,
                           resultSet.getDouble("price"),
                           resultSet.getDouble("discount")
                   ));
               }
           }
       }
       return rsl;
    }

    @Override
    public List<Food> deleteAll() throws SQLException {
        List<Food> oldFood = findAll();
        String sql = "delete from food";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.execute();
        }
        return oldFood;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
