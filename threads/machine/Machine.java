package threads.machine;

import java.time.Duration;

public class Machine implements RunnableMachine {

    private Container container;
    private Boolean consumer;

    public Machine(Container container, Boolean consumer) {
        this.container = container;
        this.consumer = consumer;
    }
    
    @Override
    public void run() {
        while (true) {
            if (isConsumer()) {
                consume((long)Math.random() * 1_000);
            } else {
                push((long)(Math.random() * 1_000));                 
            }
        }
    }

    public void push(long randMsecsInterval) {
        System.out.print(".");
        while (true) {
            if (container.val == null) {
                container.val = (int)randMsecsInterval;
                break;
            } else {
                System.out.println(Thread.currentThread().getName() + " busy with: " + container.val);
                try {
                    Thread.sleep(Duration.ofMillis(randMsecsInterval));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume(long randMsecsInterval) {
        while (true) {
            if (container.val != null) {
                System.out.print("/");
                container.val = null;
            }
            try {
                Thread.sleep(Duration.ofMillis(randMsecsInterval));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isConsumer() {
        return consumer;
    }
    
}