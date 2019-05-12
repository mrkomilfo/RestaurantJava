package sample.Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Dictionary;

public class Employee {
    public String surname;
    public String name;
    public String patronymic;
    public LocalDate birthDate;
    public String position;
    public double salary;
    public String login;
    public String password;
    public boolean menuRoot;
    public boolean menuRootReadOnly;
    public boolean employeesRoot;
    public boolean employeesRootReadOnly;
    public boolean ordersRoot;
    public boolean ordersRootReadOnly;
    private String SNP;

    public Employee(String surname, String name, String patronymic,
                    LocalDate birthDate, String position, double salary, String login, String password,
                    boolean menuRoot, boolean menuRootReadOnly, boolean employeesRoot,
                    boolean employeesRootReadOnly, boolean ordersRoot, boolean ordersRootReadOnly)
    {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.position = position;
        this.salary = salary;
        this.login = login;
        this.password = password;
        this.menuRoot = menuRoot;
        this.menuRootReadOnly = menuRootReadOnly;
        this.employeesRoot = employeesRoot;
        this.employeesRootReadOnly = employeesRootReadOnly;
        this.ordersRoot = ordersRoot;
        this.ordersRootReadOnly = ordersRootReadOnly;
        this.SNP = surname + " " + name + " " + patronymic;
    }

    public Employee() {

    }

    public String getPosition() {
        return position;
    }

    public String getSNP(){
        return SNP;
    }

    public void setSNP(String snp)
    {
        this.SNP = snp;
    }
}
