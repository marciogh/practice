package pascal;

import java.util.ArrayList;
import java.util.List;

public class Pascal {

    public List<List<Integer>> generate(int height) {
        if (height == 1) {
            List<List<Integer>> newTree = new ArrayList<List<Integer>>();
            newTree.add(List.of(1));
            return newTree;
        } else {
            List<Integer> newRow = new ArrayList<>(2 ^ height);
            newRow.add(1);
            List<List<Integer>> tree = generate(height - 1);
            List<Integer> previousRow = tree.get(tree.size() - 1);
            for (int i = 0; i < previousRow.size() - 1; i ++) {
                newRow.add(previousRow.get(i) + previousRow.get(i+1));
            }
            newRow.add(1);
            tree.add(newRow);
            return tree;
        }
    }

    public static void main(String[] args) {
        Pascal pascal = new Pascal();
        System.out.println(
            pascal.generate(20)
        );
        
    }


}
