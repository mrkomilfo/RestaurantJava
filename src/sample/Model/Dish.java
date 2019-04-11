package sample.Model;

import java.util.ArrayList;

public class Dish {
    int id;
    String type;
    String name;
    int price;
    int output;
    ArrayList<String> consist;

    public Dish(int id, String type, String name, int price, int output, String... consist)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.output = output;
        this.consist = new ArrayList<String>();
        for (String component: consist) {
            this.consist.add(component);
        }
    }
}
