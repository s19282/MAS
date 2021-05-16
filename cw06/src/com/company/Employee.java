package com.company;

import java.time.LocalDate;
enum Position {MECHANIC,PAINTER}
public class Employee
{
//    TODO: add method
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private Position position;
    private String specialization;
    private float bonusForWorkConditions;

    public Employee(String firstName, String lastName, LocalDate dateOfBirth, Position position, float bonusForWorkConditions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
        this.bonusForWorkConditions = bonusForWorkConditions;
    }

    public Employee(String firstName, String lastName, LocalDate dateOfBirth, Position position, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
        this.specialization = specialization;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position, float bonusForWorkConditions) {
        if(getPosition()==position)
            System.out.println("This position is already set!");
        else if (position==Position.MECHANIC)
        {
            System.out.println("Mechanic should have specialization not bonus!");
        }
        else
        {
            this.position = position;
            try {
                setBonusForWorkConditions(bonusForWorkConditions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setPosition(Position position, String specialization) {
        if(getPosition()==position)
            System.out.println("This position is already set!");
        else if (position==Position.PAINTER)
        {
            System.out.println("Painter should have bonus not specialization!");
        }
        else
        {
            this.position = position;
            try {
                setSpecialization(specialization);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getSpecialization() throws Exception {
        if(getPosition() == Position.MECHANIC) {
            return specialization;
        }
        throw new Exception("This employee is not a mechanic!");
    }

    public void setSpecialization(String specialization) throws Exception {
        if(getPosition() == Position.MECHANIC) {
            this.specialization = specialization;
        }
        {
            throw new Exception("This employee is not a mechanic!");
        }
    }

    public float getBonusForWorkConditions() throws Exception {
        if(getPosition() == Position.PAINTER) {
            return bonusForWorkConditions;
        }
        throw new Exception("This employee is not a painter!");
    }

    public void setBonusForWorkConditions(float bonusForWorkConditions) throws Exception {
        if(getPosition() == Position.PAINTER) {
            this.bonusForWorkConditions = bonusForWorkConditions;
        }
        else
        {
            throw new Exception("This employee is not a painter!");
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", position=" + position +
                (getPosition()==Position.MECHANIC ? ", specialization='" + specialization + '\'' :
                ", bonusForWorkConditions=" + bonusForWorkConditions) +
                '}';
    }
}
