package sample.Model;

public class Section extends Dish{
    protected int number;

    public Section(String type, String name, double price, int output) {
        super(type, name, price, output);
        number = 1;
    }

    public Section(Dish dish) {
        super(dish.type, dish.name, dish.price, dish.output);
        number = 1;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public double getPrice()
    {
        return this.price * this.number;
    }

    public void increment()
    {
        number++;
    }

    public void decrement()
    {
        number--;
    }
}
