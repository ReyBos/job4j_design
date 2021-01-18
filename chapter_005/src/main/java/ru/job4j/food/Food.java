package ru.job4j.food;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Food {
    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private double price;
    private double discount;

    public Food(
            String name, Calendar expiryDate, Calendar createDate, double price, double discount
    ) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public double getLivedTimePercent() {
        long lifetime = (getExpiryDate().getTimeInMillis() - getCreateDate().getTimeInMillis());
        long livedTime = (new GregorianCalendar()).getTimeInMillis() - getCreateDate().getTimeInMillis();
        return (double) livedTime / lifetime;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public double getDiscount() {
        return discount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(expiryDate, food.expiryDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price);
    }
}