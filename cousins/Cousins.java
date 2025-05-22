package cousins;

import java.util.HashMap;
import java.util.Map;


public class Cousins {
         
    public TreeNode replaceValueInTree(TreeNode root) {
        Map<Integer, Integer> sumLevel = new HashMap<Integer, Integer>();
        sumValueInTreeDFS(root, 0, sumLevel);
        return replaceValueInTreeDFS(root, 0, sumLevel);        
    }

    public TreeNode sumValueInTreeDFS(TreeNode node, int level, Map<Integer, Integer> sumLevel) {
        int sum = 0; int left = 0, right = 0;
        if (node.left != null) {
            left = node.left.val;
            sumValueInTreeDFS(node.left, level + 1, sumLevel);
        }
        if (node.right != null) {
            right = node.right.val;
            sumValueInTreeDFS(node.right, level + 1, sumLevel);
        }
        sum = left + right;
        sumLevel.put(level, sumLevel.getOrDefault(level, 0) + sum);
        return node;
    }

    public TreeNode replaceValueInTreeDFS(TreeNode node, int level, Map<Integer, Integer> sumLevel) {
        int sum = 0; int left = 0, right = 0, diff = 0;
        if (node.left != null) {
            left = node.left.val;
            replaceValueInTreeDFS(node.left, level + 1, sumLevel);
        }
        if (node.right != null) {
            right = node.right.val;
            replaceValueInTreeDFS(node.right, level + 1, sumLevel);
        }
        sum = left + right;
        diff = sumLevel.get(level) - sum;
        if (node.left != null) node.left.val = diff;
        if (node.right != null) node.right.val = diff;
        if (level == 0) node.val = 0;
        return node;
    }

    /*

    public record NodeLevel(TreeNode node, TreeNode parent, Integer level) {};

    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<NodeLevel> q = new LinkedList<NodeLevel>();
        Stack<NodeLevel> stack = new Stack<>();
        Stack<NodeLevel> prevStack = new Stack<>();
        NodeLevel curr = null, prev = null, next = null;
        int sum = 0;
        q.add(new NodeLevel(root, null, 0));
        while (! q.isEmpty()) {

            curr = q.poll();
            next = q.peek();

            if (next == null || curr.parent != next.parent) {
                while(! prevStack.isEmpty()) {
                    NodeLevel nl = prevStack.pop();
                    System.out.println(nl.node.val + "->" + sum);
                }
            }

            System.out.println("c: " + curr.node.val);
            if (curr.level > 1) {
                stack.add(curr);
            }

            if (
                prev != null && (
                        next == null ||
                        curr.parent != next.parent
                    )
                ) {
                    sum = 0;
                    while (! stack.empty()) {
                        NodeLevel nl = stack.pop();
                        sum+= nl.node.val;
                        prevStack.add(nl);
                    }
            }

            if (curr.node.left != null)
                q.add(new NodeLevel(curr.node.left, curr.node, curr.level + 1));
            if (curr.node.right != null)
                q.add(new NodeLevel(curr.node.right, curr.node, curr.level + 1));
            prev = curr;
        }

        return root;
    }
    */

    public static void main(String[] args) {
        
        TreeNode root = 
            new TreeNode(
                5, 
                new TreeNode(
                    4, 
                    new TreeNode(
                        1,
                        null,
                        null
                    ),
                    new TreeNode(
                        10,
                        null,
                        null
                    )
                ),
                new TreeNode(
                    9,
                    null,
                    new TreeNode(
                        7,
                        null,
                        null
                    )
                )
            );

        Cousins c = new Cousins();
        TreeNode tree = c.replaceValueInTree(root);
        System.out.println(tree);
    }

}
