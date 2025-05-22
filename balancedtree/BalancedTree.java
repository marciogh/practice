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

    private void insertBalancedRecursive(Node node, int val) {
        if (node != null) {
            if (val < node.val()) {
                if (node.left() != null && node.left().val() >= val) {
                    node = new Node(val, node.left(), node.right());
                    return;
                }
                insertBalancedRecursive(node.left(), val);
            } else {
                if (node.right() != null && node.right().val() <= val) {
                    node = new Node(val, node.left(), node.right());
                    return;
                }
                insertBalancedRecursive(node.right(), val);
            }
        }
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
            if (n.left() != null) {
                q.add(n.left());
            }
            if (n.right() != null) {
                q.add(n.right());
            }
            result.add(n.val());
        }
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
        System.out.println(bfs());
    }
    
}
