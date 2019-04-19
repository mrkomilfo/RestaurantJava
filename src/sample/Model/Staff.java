package sample.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Staff
{
    int idIterator = 0;
    private Map<Integer, Employee> personnel;

    public Staff(Employee... employees)
    {
        this.personnel = new HashMap<Integer, Employee>();
        for(Employee employee : employees)
        {
            recruit(employee);
        }
    }

    public void recruit(Employee employee)
    {
        this.personnel.put(idIterator, employee);
        idIterator++;
    }

    public void dismiss(int id)
    {
        this.personnel.remove(id);
    }

    public Employee getEmployee(int id)
    {
        return personnel.get(id);
    }

    public Map<Integer, Employee> toMap()
    {
        return personnel;
    }

    static public Staff loadStaff()
    {
        Staff start_staff = new Staff();
        //start_staff.recruit(new Employee());
        return start_staff;
    }
}
