package snake;

import java.util.ArrayList;
import java.util.List;

public class SnakeGame {

    int width = 10, height = 10;
    List<Dot> snake = new ArrayList<Dot>();

    public SnakeGame() {
        snake.add(new Dot(0, 0));
        snake.add(new Dot(1, 0));
        snake.add(new Dot(2, 0));
    }

    public boolean move(char dir) {
        Dot head = snake.getLast();
        Dot newHead = head;
        switch (dir) {
            case 'R':
                newHead = new Dot(head.x() + 1, head.y());
                break;
            case 'L':
                newHead = new Dot(head.x() - 1, head.y());
                break;
            case 'U':
                newHead = new Dot(head.x(), head.y() - 1);
                break;
            case 'D':
                newHead = new Dot(head.x(), head.y() + 1);
                break;
        }
        if (isGameOver(newHead)) {
            return false;
        } else {
            snake.add(newHead);
            snake.removeFirst();
            return true;
        }
    }

    public boolean isGameOver(Dot newHead) {
        
        boolean outOfBounds = 
            newHead.x() >= width ||
            newHead.x() < 0 ||
            newHead.y() >= height ||
            newHead.y() < 0;
        if (outOfBounds) {
            return true;
        }

        if (snake.contains(newHead)) {
            return true;
        }

        return false;

    }

}
