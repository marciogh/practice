package collecttut;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CollectTut {

    public static void main(String[] args) {

        // array
        // static size
        // O(1) random access read write
        int[] array = new int[] { 1, 2, 3, 4, 5, 6 };
        int _ = array[1];

        // arraylist
        // dynamic size, increases by copying original array to new array 50% bigger
        // O(1) random access read write
        // O(N) to increase size (needs to copy)
        // O(log N) binary search
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.get(3);

        // doubly linkedList
        // dynamic size, memory pointer
        // O(N) random read
        // O(1) random write
        // O(N) increase capacity (can use ensureCapacity)
        // O(1) read first / last
        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.get(4);

        // doubly linkedList also implements DeQue
        Deque<Integer> dequeList = (Deque<Integer>) linkedList;
        System.out.println(dequeList.poll());
        System.out.println(dequeList.pop());

        // HashSet
        // Unique items
        // backed by a HashMap
        // O(1) add contains
        // O(N) next
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        System.out.println(set.contains(1));

        // TreeSet
        // Unique items
        // Ordered
        // backed by a TreeMap
        // O(log N) add remove contains
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        System.out.println(treeSet.contains(2));
    }
}
