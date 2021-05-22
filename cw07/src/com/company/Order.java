package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order
{
    private static long counter = 0;
    private final long orderId;
    private final LocalDate dateOfPlacing;
    private String order;

    private final List<Employee> employees = new ArrayList<>();

    public Order(String order) {
        this.order = order;
        dateOfPlacing = LocalDate.now();
        orderId = counter++;
    }

    public void addEmployee(Employee employee)
    {
        if(!employees.contains(employee))
        {
            employees.add(employee);
            employee.addOrder(this);
        }
    }
    public void removeEmployee(Employee employee)
    {
        if(employees.contains(employee))
        {
            employees.remove(employee);
            employee.removeOrder(this);
        }
    }


    public long getOrderId() {
        return orderId;
    }

    public LocalDate getDateOfPlacing() {
        return dateOfPlacing;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String showOrder()
    {
        return "Order{" +
                "orderId=" + orderId +
                ", dateOfPlacing=" + dateOfPlacing +
                ", order='" + order + '\'' +
                '}';
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        employees.forEach(i->sb.append(i.showEmployee()));
        return "Order{" +
                "orderId=" + orderId +
                ", dateOfPlacing=" + dateOfPlacing +
                ", order='" + order + '\'' +
                ", employees=" + sb +
                '}';
    }
}
