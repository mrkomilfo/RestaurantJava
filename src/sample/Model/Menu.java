package sample.Model;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;
import java.util.Map;

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

    /*static public Menu loadMenu()
    {
        Menu start_menu = new Menu();
        start_menu.add(new Dish("Холодные закуски", "Салат Цезарь", 2.9, 150));
        start_menu.add(new Dish("Холодные закуски", "Салат Оливье", 3.4, 150));
        start_menu.add(new Dish("Первое блюдо", "Суп Харчо", 3.5, 300));
        start_menu.add(new Dish("Первое блюдо", "Красный борщ", 3, 300));
        start_menu.add(new Dish("Гарниры", "Пюре картофельное", 2.3, 250));
        start_menu.add(new Dish("Гарниры", "Плов с бараниной", 5, 270));
        start_menu.add(new Dish("Горячие блюда", "Драники со сметаной", 2.5, 280));
        start_menu.add(new Dish("Горячие блюда", "Котлета по-киевски", 4, 120));
        start_menu.add(new Dish("Напитки", "Чай", 2, 250));
        start_menu.add(new Dish("Напитки", "Кофе", 3, 250));
        start_menu.add(new Dish("Десерты", "Тирамису", 3.5, 180));
        start_menu.add(new Dish("Десерты", "Эклер", 2.5, 65));
        return start_menu;
    }*/
}
