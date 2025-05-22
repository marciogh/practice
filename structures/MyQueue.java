package structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {
    
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(10);
        q.add(50);
        q.add(15);
        q.add(7);
        while (! q.isEmpty()) {
            System.out.println(q.poll());
        }

    }
}
