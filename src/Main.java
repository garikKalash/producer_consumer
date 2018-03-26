import java.util.LinkedList;

public class Main
{
    public static void main(String[] args)
            throws InterruptedException
    {
        // Object of a class that has both produce()
        // and consume() methods
        final PC pc = new PC();

        // Create producer thread
        Thread producing = new Thread(() -> {
            try
            {
                pc.produce();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        // Create consumer thread
        Thread consuming = new Thread(() -> {
            try
            {
                pc.consume();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });

        // Start both threads
        producing.start();
        consuming.start();

        producing.join();
        consuming.join();
    }



}