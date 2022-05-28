package company;

import java.util.Map;

public class Cashier extends Agent
{
    public Map<String, States> cashiers_state;

    public Cashier(String name) {super(name);}

    @Override
    protected void setup()
    {
        System.out.println("My name is:" + getName());
    }

    @Override
    protected void action() throws InterruptedException
    {
        Thread.sleep(500);
        while (true)
        {
            //lets the scheduler know he is busy
            Message message = new Message();
            message.setReceiver("Scheduler");
            message.setMessage("I am free");
            dispatcher.send(message);

            //waiting for the client
            Message reply = dispatcher.receive();
            while (reply == null)
            {
                reply = dispatcher.receive();
            }

            //scheduler needs to be announced that he is busy now
            message.setReceiver("Scheduler");
            message.setMessage("busy");
            dispatcher.send(message);

            //serves the customer
            Details client_details = (Details) reply.getMessage();
            String client_name = client_details.getId();
            System.out.println(getName() + ": serving " + client_name);
            Thread.sleep(5000);

            //state of the client must be changed
            Message client_message = new Message();
            client_message.setReceiver(client_name);
            client_message.setMessage("Thank you for your choice!");
            dispatcher.send(client_message);

        }
    }
}
