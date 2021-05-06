package com.company;

import java.util.Map;
import java.util.TreeMap;

public class Employee
{
    private String firstName;
    private String lastName;
    private Integer empNumber;
    private Map<Integer,Order> ordersQualified = new TreeMap<>();


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
    public Order findOrderQualified(Integer id) throws Exception
    {
        if(!ordersQualified.containsKey(id))
        {
            throw new Exception("Order with id:" + id + " not found.");
        }
        return ordersQualified.get(id);
    }
    public void removeOrderQualified(Integer id) throws Exception
    {
        if(!ordersQualified.containsKey(id))
        {
            throw new Exception("Order with id:" + id + " not found.");
        }
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
}
