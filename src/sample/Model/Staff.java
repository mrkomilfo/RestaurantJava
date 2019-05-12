package sample.Model;

import java.util.HashMap;

public class Staff extends HashMap<String, Employee>
{
    public Staff(Employee... employees)
    {
        for(Employee employee : employees)
        {
            add(employee);
        }
    }

    public void add(Employee employee)
    {
        this.put(employee.login, employee);
    }

    public void dismiss(String login)
    {
        this.remove(login);
    }
}
