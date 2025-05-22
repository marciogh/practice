package threads;

public class VirtualThreads {    

    public static void main(String[] args) {

        Thread threadVirtual = Thread.ofVirtual().unstarted(() -> {
            System.out.println("---");
            System.out.println(Thread.currentThread().getName());
        });
        threadVirtual.setName("Sbrubles");
        threadVirtual.start();

        try {
            threadVirtual.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }


}
