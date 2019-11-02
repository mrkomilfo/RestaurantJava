package sample.Models;

public class Order extends Bill {

    protected int number;
    protected String time;

    public Order(int number, String time, Bill bill)
    {
        for (String key: bill.keySet())
        {
            this.put(key, bill.get(key));
        }
        this.time = time;
        this.number = number;
    }

    public Order(int number, String time)
    {
        this.time = time;
        this.number = number;
    }

    public String getTime()
    {
        return this.time;
    }

    public int getNumber()
    {
        return this.number;
    }
}
