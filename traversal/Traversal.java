package traversal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Traversal {

    public static void dfs(
        int node,
        boolean vis[],
        ArrayList<ArrayList<Integer>> adj,
        ArrayList<Integer> ls
    ) {

        vis[node] = true;
        ls.add(node);

        for (Integer neighbor: adj.get(node)) {
            if(vis[neighbor] == false) {
                dfs(neighbor, vis, adj, ls);
            }
        }

    }

    public ArrayList<Integer> dfsOfGraph(
        int v,
        ArrayList<ArrayList<Integer>> adj
    ) {
        boolean vis[] = new boolean[v + 1];
        vis[0] = true;
        ArrayList<Integer> ls = new ArrayList<>();
        dfs(0, vis, adj, ls);
        return ls;
    }

    public ArrayList<Integer> bfsOfGraph(
        int v,
        ArrayList<ArrayList<Integer>> adj
    ) {
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean vis[] = new boolean[v];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while (! q.isEmpty()) {
            Integer node = q.poll();
            bfs.add(node);
            for (Integer neighbour: adj.get(node)) {
                if (vis[neighbour] == false) {
                    vis[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }
        return bfs;
    }
    
}