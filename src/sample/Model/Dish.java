package sample.Model;

public class Dish {
    protected String type;
    protected String name;
    protected double price;
    protected int output;

    public Dish(String type, String name, double price, int output)
    {
        this.name = name;
        this.type = type;
        this.price = price;
        this.output = output;
    }

    public String getType()
    {
        return this.type;
    }

    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return price;
    }

    public int getOutput()
    {
        return output;
    }
}
