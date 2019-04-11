package sample.Model;

import java.util.HashMap;
import java.util.Map;

public class Staff
{
    int idIterator = 0;
    Map<Integer, Employee> personnel;

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
}
