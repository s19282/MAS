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
        //this.employee = employee; //TODO: more

        if(this.employee!=null)
        {
            if(this.employee!=employee)
            {
                this.employee.removeOrderQualified(employee.getEmpNumber());
                this.employee = employee;
                employee.addOrderQualified(this);
            }
        }
        else
        {
            this.employee = employee;
            employee.addOrderQualified(this);
        }
    }

    public void removeEmployee()
    {
        if(employee != null)
        {
            Employee tmp = employee;
            employee = null;
            tmp.removeOrderQualified(getOrderId());
        }
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

    public void setOrder(String order)
    {
        this.order = order;
    }
    public String showOrder()
    {
        return "Order{" +
                "orderId=" + orderId +
                ", dateOfPlacing=" + dateOfPlacing +
                ", order=" + order + '\'' +
                '}';
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "orderId=" + orderId +
                ", dateOfPlacing=" + dateOfPlacing +
                ", order=" + order + '\'' +
                ", employee=" + (employee != null ? employee.showEmployee() : "null") +
                '}';
    }
}
