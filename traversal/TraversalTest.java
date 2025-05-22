package traversal;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class TraversalTest {
    
    @Test
    public void dfsTest() {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(3);
        adj.get(3).add(0);
        adj.get(2).add(4);
        adj.get(4).add(2);

        Traversal s1 = new Traversal();
        ArrayList<Integer> ans = s1.dfsOfGraph(5, adj);
        assertEquals(Arrays.asList(0, 2, 4, 1, 3), ans);
    }

    @Test
    public void bfsTest() {
                ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);

        Traversal s1 = new Traversal();
        ArrayList<Integer> ans = s1.bfsOfGraph(5, adj);
        assertEquals(Arrays.asList(0, 1, 4, 2, 3), ans);
    }

}
