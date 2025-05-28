package cpu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Cpu {

    public record Task(int index, int length) {
    }

    public int[] getOrder(int[][] tasks) {
        if (tasks.length == 0) {
            return new int[] {};
        }

        Comparator<Task> shortestTask = new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                if (t1.length() == t2.length()) {
                    return t1.index() - t2.index();
                } else {
                    return t1.length() - t2.length();
                }
            }
        };

        // time to start index
        Map<Integer, List<Task>> startTimeToIndex = new HashMap<>();
        int lastStartTime = 0;
        for (int i = 0; i < tasks.length; i++) {
            int timeToStart = tasks[i][0];
            List<Task> tasksIndex = startTimeToIndex.getOrDefault(timeToStart, new ArrayList<Task>());
            tasksIndex.add(new Task(i, tasks[i][1]));
            startTimeToIndex.put(timeToStart, tasksIndex);
            if (timeToStart > lastStartTime) {
                lastStartTime = timeToStart;
            }
        }

        Queue<Task> taskQueue = new PriorityQueue<Task>(shortestTask);
        int t = 1;
        int busyTicks = 0;
        List<Integer> executionOrder = new ArrayList<>();
        while (t <= lastStartTime || !taskQueue.isEmpty()) {
            List<Task> candidateTasks = startTimeToIndex.get(t);
            if (candidateTasks != null) {
                for (Task candidateTask : candidateTasks) {
                    taskQueue.add(candidateTask);
                }
            }
            if (busyTicks == 0) {
                if (!taskQueue.isEmpty()) {
                    Task task = taskQueue.poll();
                    executionOrder.add(task.index());
                    busyTicks += task.length();
                }
            }
            if (busyTicks > 0) {
                busyTicks--;
            }
            t++;
        }
        return executionOrder.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Cpu c = new Cpu();
        int[] result = c.getOrder(new int[][] {
                { 7, 10 }, { 7, 12 }, { 7, 5 }, { 7, 4 }, { 7, 2 },
        });
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

}
