package dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {

    static ArrayList<ArrayList<ArrayList<Integer>>> constructAdj(int[][] edges, int V) {

        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            ArrayList<Integer> e1 = new ArrayList<>();
            e1.add(v);
            e1.add(wt);
            adj.get(u).add(e1);

            ArrayList<Integer> e2 = new ArrayList<>();
            e2.add(u);
            e2.add(wt);
            adj.get(v).add(e2);
        }

        return adj;

    }

    static int[] dijkstra(int V, int[][] edges, int src) {

        ArrayList<ArrayList<ArrayList<Integer>>> adj = constructAdj(edges, V);

        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(0)));

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;
        ArrayList<Integer> start = new ArrayList<>();
        start.add(0);
        start.add(src);
        pq.offer(start);

        while (!pq.isEmpty()) {

            ArrayList<Integer> curr = pq.poll();
            int d = curr.get(0);
            int u = curr.get(1);

            for (ArrayList<Integer> neighbor : adj.get(u)) {

                int v = neighbor.get(0);
                int weight = neighbor.get(1);

                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;

                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(v);
                    temp.add(dist[v]);
                    pq.offer(temp);
                }
            }

        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        int src = 0;

        // Edge list format: {u, v, weight}
        int[][] edges = {
                { 0, 1, 4 },
                { 0, 2, 8 },
                { 1, 4, 6 },
                { 2, 3, 2 },
                { 3, 4, 10 }
        };

        // Get shortest path distances
        int[] result = dijkstra(V, edges, src);

        // Print shortest distances in one line
        for (int d : result)
            System.out.print(d + " ");
    }
}
