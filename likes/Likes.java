package likes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class Likes {
    
    private Map<Integer, Integer> idToLikes = new HashMap<>();
    private TreeMap<Integer, Set<Integer>> likesToId = new TreeMap<Integer, Set<Integer>>();

    public void like(Integer id) {
        Integer likes = idToLikes.getOrDefault(id, 0);
        if (likes > 0) {
            likesToId.get(likes).remove(id);
        }
        likes+= 1;
        idToLikes.put(id, likes);
        Set<Integer> ids = likesToId.getOrDefault(likes, new HashSet<>());
        ids.add(id);
        likesToId.put(likes, ids);
    }

    public Integer mostLiked() {
        if (likesToId.size() == 0) return -1;
        return likesToId.lastEntry().getValue().stream().findAny().orElse(-1);
    }

    public List<Integer> mostLiked(int k) {
        if (likesToId.size() == 0) return List.of();
        List<Integer> ids = new ArrayList<>(k);
        int count = 0;
        Iterator<Integer> descendIterator = likesToId.descendingKeySet().iterator();
        while (descendIterator.hasNext()) {
            if (count > k) break;
            Set<Integer> descendIds = likesToId.get(descendIterator.next());
            Iterator<Integer> descendIdsIterator = descendIds.iterator();
            while(descendIdsIterator.hasNext()) {
                count = count + 1;
                if (count > k) break;
                Integer descentId = descendIdsIterator.next();
                ids.add(descentId);
            }
        }
        return ids;
    }

    @Test
    public void testLikeEmpty() {
        Likes l = new Likes();
        assertEquals(-1, l.mostLiked());
    }

    @Test
    public void testLikes() {
        Likes l = new Likes();
        l.like(1);
        l.like(1);
        l.like(2);
        l.like(4);
        l.like(2);
        l.like(2);
        l.like(2);
        l.like(2);
        assertEquals(2, l.mostLiked());
    }

    @Test
    public void testLikesK() {
        Likes l = new Likes();
        l.like(1);
        l.like(1);
        l.like(2);
        l.like(4);
        l.like(2);
        l.like(2);
        l.like(2);
        l.like(2);
        assertEquals(List.of(2), l.mostLiked(1));
        assertEquals(List.of(2, 1), l.mostLiked(2));
        assertEquals(List.of(2, 1, 4), l.mostLiked(3));
    }

}
