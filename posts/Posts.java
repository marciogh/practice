package posts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Posts {

    static Map<Integer, Integer> postToLikes = new HashMap<>();
    static TreeMap<Integer, List<Integer>> likesToPost = new TreeMap<>();

    public static void like(Integer postId) {
        int likes = postToLikes.computeIfAbsent(postId, (_) -> 1);
        postToLikes.put(postId, likes + 1);
        if (likesToPost.containsKey(likes)) {
            likesToPost.get(likes).remove(postId);
        }
        if (likesToPost.containsKey(likes + 1)) {
            likesToPost.get(likes).add(postId);
        } else {
            List<Integer> posts = new ArrayList<>();
            posts.add(postId);
            likesToPost.put(likes, posts);
        }
    }

    public static void dislike(Integer postId) {
        if (!postToLikes.containsKey(postId)) {
            return;
        }
        int likes = postToLikes.get(postId);
        if (likes == 1) {
            postToLikes.remove(postId);
        } else {
            postToLikes.put(postId, likes - 1);
        }

        if (likesToPost.containsKey(likes)) {
            likesToPost.get(likes).remove(postId);
        }
        if (likesToPost.containsKey(likes - 1)) {
            likesToPost.get(likes - 1).add(postId);
        } else {
            List<Integer> posts = new ArrayList<>();
            posts.add(postId);
            likesToPost.put(likes - 1, posts);
        }
    }

    public static int mostLiked() {
        return likesToPost.lastEntry().getValue().getFirst();
    }

    public static void main(String[] args) {

        Runnable liker = new Runnable() {
            public void run() {
                try {
                    Thread.sleep((int) (Math.random() * 10));
                } catch (Exception e) {
                    System.out.println(e);
                }
                int postId = (int) (Math.random() * 10);
                Posts.like(postId);
            }
        };

        Runnable disliker = new Runnable() {
            public void run() {
                try {
                    Thread.sleep((int) (Math.random() * 10));
                } catch (Exception e) {
                    System.out.println(e);
                }
                int postId = (int) (Math.random() * 10);
                Posts.dislike(postId);
            }
        };

        Runnable mostLiked = new Runnable() {
            public void run() {
                try {
                    Thread.sleep((int) (Math.random() * 10));
                } catch (Exception e) {
                    System.out.println(e);
                }
                Posts.mostLiked();
            }
        };

        int threads = 1000;
        int concurrency = 10000;
        ExecutorService es = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < concurrency; i++) {
            es.submit(liker);
            es.submit(disliker);
            es.submit(mostLiked);
        }
        es.close();
        for (int key : postToLikes.keySet()) {
            System.out.println(key + "=" + postToLikes.get(key));
        }
        System.out.println("--");
        for (int key : likesToPost.keySet()) {
            System.out.println(key + "=" + likesToPost.get(key));
        }
        System.out.println(mostLiked());

    }
}
