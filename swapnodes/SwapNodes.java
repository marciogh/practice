package swapnodes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SwapNodes {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }

    public ListNode flattenToString(ListNode head, StringBuffer s) {
        if (head == null) {
            return null;
        }
        s.append(head.val + " ");
        return flattenToString(head.next, s);
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        SwapNodes s = new SwapNodes();

        StringBuffer sb = new StringBuffer();
        s.flattenToString(head, sb);
        System.out.println(sb.toString());

        head = s.swapPairs(head);
        sb = new StringBuffer();
        s.flattenToString(head, sb);
        System.out.println(sb.toString());
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        SwapNodes s = new SwapNodes();
        s.swapPairs(head);
        StringBuffer sb = new StringBuffer();
        s.flattenToString(head, sb);
        assertEquals("1 2 3 4 ", sb.toString());
    }
}
