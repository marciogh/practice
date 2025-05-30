package reverselist;

public class ReverseList {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode n = head;
        ListNode previous = null;
        ListNode previousPLeft = null;
        ListNode pLeft = null;
        ListNode pRight = null;
        while (n != null) {
            if (n.val == left) {
                previousPLeft = previous;
                pLeft = n;
            }
            if (n.val == right) {
                pRight = n;
                previousPLeft.next = pRight.next;
                pRight.next.next = pLeft;
                pRight.next = null;
            }
            if (n.next == null) {

            }
            previous = n;
            n = n.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode myHead = head;
        while (myHead != null) {
            System.out.print(myHead.val + "->");
            myHead = myHead.next;
        }
        System.out.println("");

        ReverseList r = new ReverseList();
        ListNode newHead = r.reverseBetween(head, 2, 4);
        while (newHead != null) {
            System.out.print(newHead.val + "->");
            newHead = newHead.next;
        }
    }
}
