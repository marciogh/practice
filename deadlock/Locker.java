package deadlock;

public class Locker {

    Object lock1;
    Object lock2;

    public Locker() {
        lock1 = new Object();
        lock2 = new Object();
    }

    public static void work() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void process1() {
        System.out.println("Process 1");
        synchronized (lock1) {
            work();
            process2();
            System.out.println("Done process 1");
        }
    }

    public void process2() {
        System.out.println("Process 2");
        synchronized (lock2) {
            work();
            process1();
            System.out.println("Done process 2");
        }
    }

    public static void main(String[] args) {
        Locker locker = new Locker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                locker.process1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                locker.process2();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
