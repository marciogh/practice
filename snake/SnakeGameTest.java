package snake;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SnakeGameTest {
    
    @Test
    public void testOutOfBoundsRight() {
        SnakeGame g = new SnakeGame();
        for (int i = 0; i < 7; i ++) {
            assertTrue(g.move('R'));
        }
        assertFalse(g.move('R'));
    }

    @Test
    public void testOutOfBoundsUp() {
        SnakeGame g = new SnakeGame();
        assertFalse(g.move('U'));
    }

    @Test
    public void testOutOfBoundsDown() {
        SnakeGame g = new SnakeGame();
        for (int i = 0; i < 9; i ++) {
            assertTrue(g.move('D'));
        }
        assertFalse(g.move('D'));
    }

    @Test
    public void testEatItself() {
        SnakeGame g = new SnakeGame();
        for (int i = 0; i < 3; i ++) {
            assertTrue(g.move('R'));
        }
        assertFalse(g.move('L'));
    }

}
