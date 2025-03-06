public class ThreadMonitoring extends Thread{
    public void run() {
        System.out.println("This code is running in a thread");
    }

    public static void main(String[] args)
    {
        ThreadMonitoring thread = new ThreadMonitoring();
        thread.start();
    }
}
