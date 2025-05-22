import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class BalancedTree {
    
    Node root = null;

    public void insert(int val) {
        if (root == null) {
            root = new Node(val, null, null);
        } else {
            insertBalancedRecursive(root, val);
        }
    }

    private Node insertBalancedRecursive(Node node, int val) {
        if (node == null) {
            return new Node(val, null, null);
        }
        if (val < node.val) {
            node.left = insertBalancedRecursive(node.left, val);
        } else {
            node.right = insertBalancedRecursive(node.right, val);
        }
        return node;

    }

    private List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while(! q.isEmpty()) {
            Node n = q.poll();
            if (n.left != null) {
                q.add(n.left);
            }
            if (n.right != null) {
                q.add(n.right);
            }
            result.add(n.val);
        }
        return result;
    }

    private List<Integer> dfs() {
        List<Integer> result = new ArrayList<>();
        return dfsRecursive(root, result);
    }

    private List<Integer> dfsRecursive(Node node, List<Integer> result) {
        if (node == null) {
            return result;
        }
        result.add(node.val);
        dfsRecursive(node.left, result);
        dfsRecursive(node.right, result);
        return result;
    }


    @Test
    public void insertItems() {
        BalancedTree tree = new BalancedTree();
        tree.insert(10);
        tree.insert(12);
        tree.insert(3);
        tree.insert(5);
        tree.insert(14);
        tree.insert(8);
        System.out.println("insertItems test");
        System.out.println(tree.bfs());
        System.out.println(tree.dfs());
    }
    
}
