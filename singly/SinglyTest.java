package singly;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class SinglyTest {
    
    @Test
    public void testBuild() {

        Node n1 = Node.from(150);
        assertEquals(0, n1.val);
        assertEquals(5, n1.next.val);
        assertEquals(1, n1.next.next.val);
        assertNull(n1.next.next.next);

        Node n2 = Node.from(999);
        assertEquals(9, n2.val);
        assertEquals(9, n2.next.val);
        assertEquals(9, n2.next.next.val);
        assertNull(n2.next.next.next);

        Node n3 = Node.from(0);
        assertEquals(0, n3.val);
        assertNull(n3.next);

    }

    @Test
    public void sumTest() {
        Singly s = new Singly();
        Node n1 = Node.from(12);
        Node n2 = Node.from(34);
        Node sum = s.sum(n1, n2);
        assertEquals(6, sum.val);
        assertEquals(4, sum.next.val);
        assertNull(sum.next.next);
    }

    @Test
    public void sumTestDiffLength() {
        Singly s = new Singly();
        Node n1 = Node.from(111);
        Node n2 = Node.from(34);
        Node sum = s.sum(n1, n2);
        assertEquals(5, sum.val);
        assertEquals(4, sum.next.val);
        assertEquals(1, sum.next.next.val);
        assertNull(sum.next.next.next);

        n1 = Node.from(74);
        n2 = Node.from(123);
        sum = s.sum(n1, n2);
        assertEquals(7, sum.val);
        assertEquals(9, sum.next.val);
        assertEquals(1, sum.next.next.val);
        assertNull(sum.next.next.next);
    }

    @Test
    public void sumTestPlusOne() {
        Singly s = new Singly();
        Node n1 = Node.from(9);
        Node n2 = Node.from(2);
        Node sum = s.sum(n1, n2);
        assertEquals(1, sum.val);
        assertEquals(1, sum.next.val);
        assertNull(sum.next.next);
    }

    @Test
    public void sumTestPlusOneComplex() {
        Singly s = new Singly();
        Node n1 = Node.from(999);
        Node n2 = Node.from(2);
        Node sum = s.sum(n1, n2);
        assertEquals(1, sum.val);
        assertEquals(0, sum.next.val);
        assertEquals(0, sum.next.next.val);
        assertEquals(1, sum.next.next.next.val);
        assertNull(sum.next.next.next.next);
    }    
    
}
