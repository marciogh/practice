package futuretutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTutorial {

    public static void main(String[] args) {
        List<CompletableFuture<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            tasks.add(CompletableFuture.supplyAsync(() -> "hello"));
        }

        CompletableFuture<List<String>> results = CompletableFuture.completedFuture(new ArrayList<>());
        for (CompletableFuture<String> task : tasks) {
            results = results.thenCompose(r -> {
                try {
                    r.add(task.get());
                } catch (Exception e) {
                    System.out.println(e);
                }
                return CompletableFuture.completedFuture(r);
            });
        }

        System.out.println(results.join());

    }
}
