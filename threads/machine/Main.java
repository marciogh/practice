package threads.machine;

public class Main {
    
    public static void main(String[] args) {
        Container container = new Container(null);
        RunnableMachine producer = new Machine(container, false);
        RunnableMachine consumer = new Machine(container, true);

        Thread tProducer = Thread.ofVirtual().unstarted(producer);
        tProducer.setName("producer");
        Thread tConsumer = Thread.ofVirtual().unstarted(consumer);
        tConsumer.setName("consumer");

        tProducer.start();
        tConsumer.start();

        System.out.println(producer.isConsumer());
        System.out.println(consumer.isConsumer());

        try {
            tProducer.join();
            tConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
