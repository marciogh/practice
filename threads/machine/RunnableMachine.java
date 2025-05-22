package threads.machine;

public interface RunnableMachine extends Runnable {

    public void push(long randMsecsInterval);
    public void consume(long randMsecsInterval);
    public boolean isConsumer();

}
