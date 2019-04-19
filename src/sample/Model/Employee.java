package sample.Model;

import java.util.Date;

public class Employee {
    String name;
    String surname;
    String patronymic;
    Date birthDate;
    String position;
    Date employmentDate;
    int salary;

    public Employee(String name, String surname, String patronymic,
                    Date birthDate, String position, Date employmentDate, int salary)
    {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.position = position;
        this.employmentDate = employmentDate;
        this.salary = salary;
    }
}
