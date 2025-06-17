package fibonacci;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

  public int fib(int n) {
    return fibM(n, new HashMap<Integer, Integer>());
  }

  int fibM(int n, Map<Integer, Integer> memo) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    if (memo.containsKey(n)) {
      return memo.get(n);
    } else {
      int next = fibM(n - 1, memo) + fibM(n - 2, memo);
      memo.put(n, next);
      return next;
    }
  }

  public static void main(String[] args) {
    Fibonacci f = new Fibonacci();
    System.out.println(f.fib(10));
  }
}
