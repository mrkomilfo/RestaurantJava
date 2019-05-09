package sample.Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class Staff extends HashMap<String, Employee>
{
    public Staff(Employee... employees)
    {
        for(Employee employee : employees)
        {
            recruit(employee);
        }
    }

    public void recruit(Employee employee)
    {
        this.put(employee.login, employee);
    }

    public void dismiss(String login)
    {
        this.remove(login);
    }

    static public Staff loadStaff()
    {
        Staff start_staff = new Staff();
        start_staff.recruit(new Employee("Дорофеев", "Валентин", "Игоревич",
                 LocalDate.of(2000, 02, 17), "Главный администратор", 500, "komilfo",
                "1312", true, false, true, false,
                true, false));
        return start_staff;
    }
}
