package com.company;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Order
{
    private static int counter = 0;
    private int orderId;
    private LocalDate dateOfPlacing;
    private String order;
    private Employee employee;

    public Order(String order)
    {
        orderId = counter++;
        dateOfPlacing = LocalDate.now();
        this.order = order;
    }

    public void addEmployee(Employee employee) {
        this.employee = employee; //TODO: more
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Order.counter = counter;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateOfPlacing() {
        return dateOfPlacing;
    }

    public void setDateOfPlacing(LocalDate dateOfPlacing) {
        this.dateOfPlacing = dateOfPlacing;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
