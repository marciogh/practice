package futuretutorialII;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class F {

    public static void main(String[] args) {

        /*
         * ExecutorService es = Executors.newFixedThreadPool(3);
         * Future<Integer> fi = es.submit(() -> {
         * return 1;
         * });
         * try {
         * System.out.println(fi.get());
         * } catch (InterruptedException ie) {
         * System.out.println(ie);
         * } catch (ExecutionException ee) {
         * System.out.println(ee);
         * }
         */

        ExecutorService es = Executors.newFixedThreadPool(2);
        CompletableFuture<String> begin = CompletableFuture.supplyAsync(() -> "Start-" + Thread.currentThread());
        List<CompletableFuture<String>> fs = new ArrayList<>();
        fs.add(begin);
        for (int i = 0; i < 10; i++) {
            final String content = String.valueOf(i);
            CompletableFuture<String> fss;
            if (i == 5) {
                fss = CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Failing on 5");
                }, es);
            } else {
                fss = CompletableFuture.supplyAsync(() -> content + "-" + Thread.currentThread(), es);
            }
            fs.add(fss.handle(
                    (str, ex) -> str != null ? str : ex.getMessage().toString() + "-" + Thread.currentThread()));
        }
        List<String> results = new ArrayList<>(fs.size());
        for (CompletableFuture<String> c : fs) {
            results.add(c.join());
        }
        for (String r : results) {
            System.out.println(r);
        }

        es.close();
    }
}
