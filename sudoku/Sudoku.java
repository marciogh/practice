package sudoku;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class Sudoku {

    public boolean isValidSudoku(char[][] board) {

        int i = 0;
        int j = 0;
        int width = 0;

        Map<Integer, List<Character>> collumStacks = new HashMap<Integer, List<Character>>();
        Map<String, List<Character>> quadrantStacks = new HashMap<String, List<Character>>();
        List<Character> lineStack = new ArrayList<>();

        for (i = 0; i < board.length; i++) {

            lineStack.clear();

            for (j = 0; j < board[i].length; j++) {

                if (board[i][j] != '.') {
                    if (lineStack.contains(board[i][j])) {
                        return false;
                    } else {
                        lineStack.add(board[i][j]);
                    }

                    List<Character> collumStack = collumStacks.getOrDefault(j, new ArrayList<>());
                    if (collumStack.contains(board[i][j])) {
                        return false;
                    } else {
                        collumStack.add(board[i][j]);
                    }
                    collumStacks.put(j, collumStack);

                    String quadrant = (int) i / 3 + "-" + (int) j / 3;
                    List<Character> quadrantStack = quadrantStacks.getOrDefault(quadrant, new ArrayList<>());
                    if (quadrantStack.contains(board[i][j])) {
                        return false;
                    } else {
                        quadrantStack.add(board[i][j]);
                    }
                    quadrantStacks.put(quadrant, quadrantStack);

                }
            }
            if (width != 0 && width != j) {
                return false;
            }
            width = j;

        }
        return true;
    }

    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        char[][] board = new char[][] {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        s.isValidSudoku(board);
    }

    @Test
    public void test() {
        Sudoku s = new Sudoku();

        assertTrue(s.isValidSudoku(new char[][] {
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }
        }));

        assertFalse(s.isValidSudoku(new char[][] {
                { '.', '.', '.', '.', '5', '.', '.', '1', '.' },
                { '.', '4', '.', '3', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '3', '.', '.', '1' },
                { '8', '.', '.', '.', '.', '.', '.', '2', '.' },
                { '.', '.', '2', '.', '7', '.', '.', '.', '.' },
                { '.', '1', '5', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '2', '.', '.', '.' },
                { '.', '2', '.', '9', '.', '.', '.', '.', '.' },
                { '.', '.', '4', '.', '.', '.', '.', '.', '.' }
        }));

        assertFalse(s.isValidSudoku(new char[][] {
                { '5', '3', '.', '.', '7', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        }));

        assertTrue(s.isValidSudoku(new char[][] {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        }));

    }
}
