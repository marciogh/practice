package structures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyGraph {

    public static List<Node> bfs(Node node) {
        List<Node> bfs = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (! q.isEmpty()) {
            Node next = q.poll();
            if (! bfs.contains(next)) {
                bfs.add(next);
            }
            for (Node child : next.children) {
                if (! bfs.contains(child)) {
                    q.add(child);
                }
            }
        }
        return bfs;
    }

    public static List<Node> dfs(Node node) {
        return dfsRecursive(node, new ArrayList<Node>());
    }

    public static List<Node> dfsRecursive(Node node, List<Node> dfs) {
        for (Node child: node.children) {
            if (! dfs.contains(child)) {
                dfs.add(child);
                dfsRecursive(child, dfs);
            }
        }
        return dfs;
    }

    public static void main(String[] args) {
        /*
         *            1          5 ----------\
         *              \    /   |   \        |
         *                2      4      9  -  3
         *                              |  \ 
         *                             12    10
         */
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node12 = new Node(12);

        node1.children = List.of(node2);
        node2.children = List.of(node1, node5);
        node3.children = List.of(node5, node9);
        node4.children = List.of(node5);
        node5.children = List.of(node2, node3, node4, node9);
        node9.children = List.of(node3, node5, node10, node12);
        node10.children = List.of(node9);
        node12.children = List.of(node9);

        for (Node node : bfs(node5)) {
            System.out.println(node.val);
        }
        
        System.out.println("------------");

        for (Node node : dfs(node5)) {
            System.out.println(node.val);
        }
    }


    
}
