package singly;

public class Singly {
    
    public Node sum(Node n1, Node n2) {
        Node sum = new Node();
        Node sumRoot = sum;
        boolean plusOne = false;
        while (true) {
            Integer val1, val2;
            if (n1 != null) val1 = n1.val;
            else val1 = 0;
            if (n2 != null) val2 = n2.val;
            else val2 = 0;       
            sum.val = val1 + val2;
            if (plusOne) sum.val+= 1;
            if (sum.val > 9) {
                sum.val = sum.val - 10;
                plusOne = true;
            } else plusOne = false;
           if (
                (n1 != null && n1.next != null)
                ||
                (n2 != null && n2.next != null)
                || plusOne
            ) {
                Node sumNext = new Node();
                sum.next = sumNext;
                sum = sum.next;
                if (n1 != null) n1 = n1.next;
                if (n2 != null) n2 = n2.next;
            } else break;
        }
        return sumRoot;
    }

}
