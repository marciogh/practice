package structures;

import java.util.PriorityQueue;
import java.util.Queue;

public class Priority {
    
    public static void main(String[] args) {
        Queue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(9);
        pq.add(4);
        pq.add(10);
        while(! pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
