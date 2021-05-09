package com.company;

import java.util.Map;
import java.util.TreeMap;

public class Employee
{
    private String firstName;
    private String lastName;
    private Integer empNumber;
    private final Map<Integer,Order> ordersQualified = new TreeMap<>();


    public Employee(String firstName, String lastName, Integer empNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empNumber = empNumber;
    }

    public void addOrderQualified(Order order)
    {
        if(!ordersQualified.containsKey(order.getOrderId()))
        {
            ordersQualified.put(order.getOrderId(), order);
            order.addEmployee(this);
        }
    }
    public Order findOrderQualified(Integer id)
    {
        if(!ordersQualified.containsKey(id))
        {
            try
            {
                throw new Exception("Order with id:" + id + " not found.");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return ordersQualified.get(id);
    }
    public void removeOrderQualified(Integer id)
    {
        if(!ordersQualified.containsKey(id))
        {
            try
            {
                throw new Exception("Order with id:" + id + " not found.");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        ordersQualified.get(id).removeEmployee();
        ordersQualified.remove(id);
//        TODO: more
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

    public Integer getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(Integer empNumber) {
        this.empNumber = empNumber;
    }
    public String showEmployee()
    {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empNumber=" + empNumber +
                '}';
    }

    @Override
    public String toString()
    {
        StringBuilder ordersString = new StringBuilder();
        ordersQualified.forEach((k,v)-> ordersString.append(v.showOrder()));

        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empNumber=" + empNumber +
                ", ordersQualified=[" + ordersString +
                "]}";
    }
}
