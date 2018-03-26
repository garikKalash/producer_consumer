import java.util.LinkedList;

/**
 * Created by Garik Kalashyan on 27-Mar-18.
 *
 *  This class has a parts, producer (adds items to parts and consumber (removes items).
 */
public class PC
{
    // Create a parts shared by producer and consumer
    // Size of parts is 2.
    LinkedList<Integer> parts = new LinkedList<>();
    int capacity = 2;

    // Function called by producer thread
    public void produce() throws InterruptedException
    {
        int value = 0;
        while (true)
        {
            synchronized (this)
            {
                // producer thread waits while parts
                // is full
                while (parts.size() == capacity){
                    wait();
                }

                System.out.println("Producer produced-" + value);

                // to insert the jobs in the parts
                parts.add(value++);

                // notifies the consumer thread that
                // now it can start consuming
                notify();

            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException
    {
        while (true)
        {
            synchronized (this)
            {
                // consumer thread waits while parts
                // is empty
                while (parts.size() == 0)
                    wait();

                //to retrive the ifrst job in the parts
                int val = parts.removeFirst();

                System.out.println("Consumer consumed-" + val);

                // Wake up producer thread
                notify();
            }
        }
    }
}
