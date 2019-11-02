package sample.Models;

import java.util.HashMap;

public class Menu extends HashMap<String, Dish>
{
    public Menu(Dish... dishes)
    {
        for(Dish dish : dishes)
        {
            add(dish);
        }
    }

    public void add(Dish dish)
    {
        this.put(dish.getName(), dish);
    }
}
