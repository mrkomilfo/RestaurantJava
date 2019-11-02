package sample.Models;

import java.time.LocalDate;

public class Employee {
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthDate;
    private String position;
    private double salary;
    private String login;
    private String password;
    private boolean menuRoot;
    private boolean menuRootReadOnly;
    private boolean employeesRoot;
    private boolean employeesRootReadOnly;
    private boolean ordersRoot;
    private boolean ordersRootReadOnly;
    private String SNP;

    public Employee(String surname, String name, String patronymic,
                    LocalDate birthDate, String position, double salary, String login, String password,
                    boolean menuRoot, boolean menuRootReadOnly, boolean employeesRoot,
                    boolean employeesRootReadOnly, boolean ordersRoot, boolean ordersRootReadOnly)
    {
        this.setSurname(surname);
        this.setName(name);
        this.setPatronymic(patronymic);
        this.setBirthDate(birthDate);
        this.setPosition(position);
        this.setSalary(salary);
        this.setLogin(login);
        this.setPassword(password);
        this.setMenuRoot(menuRoot);
        this.setMenuRootReadOnly(menuRootReadOnly);
        this.setEmployeesRoot(employeesRoot);
        this.setEmployeesRootReadOnly(employeesRootReadOnly);
        this.setOrdersRoot(ordersRoot);
        this.setOrdersRootReadOnly(ordersRootReadOnly);
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

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isMenuRoot() {
        return menuRoot;
    }

    public boolean isMenuRootReadOnly() {
        return menuRootReadOnly;
    }

    public boolean isEmployeesRoot() {
        return employeesRoot;
    }

    public boolean isEmployeesRootReadOnly() {
        return employeesRootReadOnly;
    }

    public boolean isOrdersRoot() {
        return ordersRoot;
    }

    public boolean isOrdersRootReadOnly() {
        return ordersRootReadOnly;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMenuRoot(boolean menuRoot) {
        this.menuRoot = menuRoot;
    }

    public void setMenuRootReadOnly(boolean menuRootReadOnly) {
        this.menuRootReadOnly = menuRootReadOnly;
    }

    public void setEmployeesRoot(boolean employeesRoot) {
        this.employeesRoot = employeesRoot;
    }

    public void setEmployeesRootReadOnly(boolean employeesRootReadOnly) {
        this.employeesRootReadOnly = employeesRootReadOnly;
    }

    public void setOrdersRoot(boolean ordersRoot) {
        this.ordersRoot = ordersRoot;
    }

    public void setOrdersRootReadOnly(boolean ordersRootReadOnly) {
        this.ordersRootReadOnly = ordersRootReadOnly;
    }
}
