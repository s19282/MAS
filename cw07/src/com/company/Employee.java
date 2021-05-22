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
        setFirstName(firstName);
        setLastName(lastName);
        setEmpNumber(empNumber);
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
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", empNumber=" + getEmpNumber() +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        orders.forEach(i->sb.append(i.showOrder()));
        return "Employee{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", empNumber=" + getEmpNumber() +
                ", orders=" + sb +
                '}';
    }
}
