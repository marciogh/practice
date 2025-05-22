package singly;

public class Node {
    int val;
    Node next;

    public Node() {}
    
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public static Node from(Integer num) {
        String numString = num.toString();
        Node node = new Node();
        Node root = node;
        for (int i = numString.length() - 1; i >= 0; i --) {
            node.val = Character.getNumericValue(numString.charAt(i));
            if (i > 0) {
                Node next = new Node();
                node.next = next;
                node = next;
            }
        }
        return root;
    }

}
