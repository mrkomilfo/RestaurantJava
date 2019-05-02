package sample.Model;

import java.util.Date;
import java.util.Dictionary;

public class Employee {
    String name;
    String surname;
    String patronymic;
    Date birthDate;
    String position;
    int salary;
    String login;
    String password;

    public Employee(String name, String surname, String patronymic,
                    Date birthDate, String position, int salary, String login, String password)
    {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.position = position;
        this.salary = salary;
        this.login = login;
        this.password = password;
    }
}
