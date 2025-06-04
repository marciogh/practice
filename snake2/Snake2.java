package snake2;

import java.util.ArrayDeque;
import java.util.Deque;

public class Snake2 {

    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            dq.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(dq.pop());
        }

    }
}
