import company.Cashier;
import company.Customer;
import company.Details;
import company.Scheduler;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        List<Customer> customers = new ArrayList<>();
        List<Cashier> cashiers = new ArrayList<>();

        //initialising customers
        for (int i=0;i<10;i++)
        {
            String name = "Customer:" + i;
            customers.add(new Customer(name));
        }

        Scheduler scheduler = new Scheduler("Scheduler");

        //initialising cashiers
        for(int i=0;i<3;i++){
            String name = "Cashier:"+i;
            cashiers.add(new Cashier(name));
        }

        for(Cashier c:cashiers){
            c.start();
        }

        scheduler.start();

        for (Customer c:customers)
        {
            c.start();
        }
    }
}
