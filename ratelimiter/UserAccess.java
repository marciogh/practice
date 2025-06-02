package ratelimiter;

public class UserAccess {
    long time;
    int counter;

    public UserAccess(long time, int counter) {
        this.time = time;
        this.counter = counter;
    }
}