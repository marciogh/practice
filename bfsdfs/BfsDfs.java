package bfsdfs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class BfsDfs {
    
    public static record Node (
        int val,
        Node left,
        Node right
    ) {}

    public List<Integer> bfs(Node root) {
        List<Integer> bfs = new ArrayList<>();
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (! q.isEmpty()) {
            Node next = q.poll();
            bfs.add(next.val);
            if (next.left != null) q.add(next.left);
            if (next.right != null) q.add(next.right);
        }
        return bfs;
    }

    public List<Integer> dfs(Node root) {
        return dfsRecursive(root, new ArrayList<Integer>());
    }

    public List<Integer> dfsRecursive(Node node, List<Integer> dfs) {
        if (node != null) dfs.add(node.val);
        if (node.left != null) dfsRecursive(node.left, dfs);
        if (node.right != null) dfsRecursive(node.right, dfs);
        return dfs;
    }

    @Test
    public void testNode() {
        Node root = new Node(1,
            new Node(2, 
                new Node(5, null, null),
                new Node(4,
                    new Node(3, null, null),
                    null
                )
            ),
            new Node(3, 
                new Node(2, null, null),
                new Node(4, null, null)
            )
        );
        /*
         *                   1
         *              /        \
         *            2            3
         *         /     \       /    \
         *        5        4    2       4
         *               /
         *              3
         */
        assertEquals(
            List.of(1, 2, 3, 5, 4, 2, 4, 3),
            bfs(root)
        );
        assertEquals(
            List.of(1,2,5,4,3,3,2,4),
            dfs(root)
        );
    }

}
