package sample.Model;

import java.util.HashMap;
import java.util.Map;

public class Menu
{
    int idIterator = 0;
    Map<Integer, Dish> dishes;

    public Menu(Dish... dishes)
    {
        this.dishes = new HashMap<Integer, Dish>();
        for(Dish dish : dishes)
        {
            add(dish);
        }
    }

    public void add(Dish dish)
    {
        this.dishes.put(idIterator, dish);
        idIterator++;
    }

    public void delete(int id)
    {
        this.dishes.remove(id);
    }
}
