package com.company;

import java.util.ArrayList;
import java.util.List;

public class Employee
{
    private String firstName;
    private String lastName;
    private long empNumber;
    private final List<Order> orders = new ArrayList<>();

    public Employee(String firstName, String lastName, long empNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empNumber = empNumber;
    }

    public void addOrder(Order order)
    {
        if(!orders.contains(order))
        {
            orders.add(order);
            order.addEmployee(this);
        }
    }
    public void removeOrder(Order order)
    {
        if(orders.contains(order))
        {
            orders.remove(order);
            order.removeEmployee(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(long empNumber) {
        this.empNumber = empNumber;
    }

    public String showEmployee() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empNumber=" + empNumber +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        orders.forEach(i->sb.append(i.showOrder()));
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empNumber=" + empNumber +
                ", orders=" + sb +
                '}';
    }
}
