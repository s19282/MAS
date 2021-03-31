package com.company;

public class Student
{
    String firstName;
    String lastName;
    int studentId;

    public Student(String firstName, String lastName, int studentId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
