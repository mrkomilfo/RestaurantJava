package sample.Model;

import java.util.HashMap;

public class Bill extends HashMap<String, Section>{

    public Bill(Section... sections)
    {
        for(Section section: sections)
        {
            add(section);
        }
    }

    public void add(Dish dish)
    {
        if (containsKey(dish.getName()))
        {
            this.get(dish.getName()).increment();
        }
        else {
            this.put(dish.getName(), new Section(dish));
        }
    }

    public void substract(String key)
    {
        if (this.get(key).getNumber() > 1)
        {
            this.get(key).decrement();
        }
        else {
            this.remove(key);
        }
    }

    public int getCost()
    {
        int total = 0;
        for (Section section: this.values())
        {
            total += section.getPrice();
        }
        return total;
    }
}
