package sample.Model;

public class Section extends Dish{
    protected int quantity;

    public Section(String type, String name, double price, int output, int quantity) {
        super(type, name, price, output);
        this.quantity = quantity;
    }

    public Section(Dish dish) {
        super(dish.type, dish.name, dish.price, dish.output);
        quantity = 1;
    }

    public int getQuantity()
    {
        return quantity;
    }

    @Override
    public double getPrice()
    {
        return this.price * this.quantity;
    }

    public void increment()
    {
        quantity++;
    }

    public void decrement()
    {
        quantity--;
    }
}
