package com.company;

import java.time.LocalDate;

public class Person
{
    String firstName;
    String lastName;
    Sex sex;
    LocalDate dateOfBirth;

    public Person(String firstName, String lastName, Sex sex, LocalDate dateOfBirth)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    public void calculateAge()
    {
        LocalDate date = LocalDate.now().minusYears(dateOfBirth.getYear()).minusMonths(dateOfBirth.getMonth().getValue()).minusDays(dateOfBirth.getDayOfMonth());
        System.out.println(date.getYear()+" years "+date.getMonthValue()+" months "+date.getDayOfMonth()+" days");
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
