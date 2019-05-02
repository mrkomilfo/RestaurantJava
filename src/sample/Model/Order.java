package sample.Model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Order extends Bill {

    protected int number;
    protected Date time;

    public Order(int number, Bill bill)
    {
        for (String key: bill.keySet())
        {
            this.put(key, bill.get(key));
        }
        this.time = new Date();
        this.number = number;
    }

    public String getTime()
    {
        SimpleDateFormat formatForOrderTime = new SimpleDateFormat("hh:mm:ss");
        return formatForOrderTime.format(this.time);
    }

    public int getNumber()
    {
        return this.number;
    }
}
