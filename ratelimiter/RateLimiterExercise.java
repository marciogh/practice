package ratelimiter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/*
 *
 * 5 Rate Limiter Strategies:
 * 
 * Fixed Time Interval Bucket  <- allow bursts, less accurate on fixed interval edges
 * Token Bucket                <- same as above, different impl
 * Leaky Bucket                <- steady rate, better for APIs
 * Sliding Window              <- allow bursts, more accurate, stores all timestamps on a FIFO, easy implementation, uses more memory
 * Sliding Window Counter      <- allow bursts, store count of current and previous window, less computation and memory
 * 
 */

public class RateLimiterExercise {

    public interface RateLimiter {
        public boolean hasAccess(UUID user, long currentTime);
    }

    public static class SlidingWindow implements RateLimiter {

        final int windowSize = 10;
        final int limit = 5;

        Map<UUID, Deque<Long>> userLog = new ConcurrentHashMap<>();

        @Override
        public boolean hasAccess(UUID user, long currentTime) {
            Deque<Long> log = userLog.computeIfAbsent(user, (_) -> new LinkedList<>());
            while (!log.isEmpty() && log.peekFirst() < currentTime - windowSize) {
                log.removeFirst();
            }
            if (log.size() >= limit) {
                return false;
            } else {
                log.add(currentTime);
                return true;
            }
        }

    }

    public static class SlidingWindowCounter implements RateLimiter {

        final int windowSize = 10;
        final int limit = 5;

        Map<UUID, Map<Long, Long>> userLog = new HashMap<>();

        @Override
        public boolean hasAccess(UUID user, long currentTime) {
            Map<Long, Long> logCounter = userLog.computeIfAbsent(user, (_) -> new HashMap<>());
            long windowKey = currentTime / windowSize;
            long counter = logCounter.computeIfAbsent(windowKey, (_) -> 0L);
            long previousCounter = logCounter.computeIfAbsent(windowKey - 1, (_) -> 0L);
            double currentWindowWeight = (double) (currentTime % windowSize) / windowSize;
            double weight = (double) (1 - currentWindowWeight) * previousCounter + counter;
            if (weight < limit) {
                logCounter.put(windowKey, ++counter);
                return true;
            }
            return false;
        }

    }

    @Test
    public void testSlidingWindow() {

        RateLimiter sl = new SlidingWindow();
        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();

        long frozenCurrentTimeInMillis = 1749026036595L;
        for (int i = 0; i < 6; i++) {
            long accessTime = frozenCurrentTimeInMillis / 1000;
            if (i < 5) {
                assertTrue(sl.hasAccess(user1, accessTime));
                assertTrue(sl.hasAccess(user2, accessTime));
            } else {
                assertFalse(sl.hasAccess(user1, accessTime));
                assertFalse(sl.hasAccess(user2, accessTime));
            }
        }

        frozenCurrentTimeInMillis = frozenCurrentTimeInMillis + (10_1000); // advance 10 seconds
        for (int i = 0; i < 6; i++) {
            long accessTime = frozenCurrentTimeInMillis / 1000;
            if (i < 5) {
                assertTrue(sl.hasAccess(user1, accessTime));
                assertTrue(sl.hasAccess(user2, accessTime));
            } else {
                assertFalse(sl.hasAccess(user1, accessTime));
                assertFalse(sl.hasAccess(user2, accessTime));
            }
        }
    }

    @Test
    public void testSlidingWindowCounter() {

        RateLimiter slc = new SlidingWindowCounter();
        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();

        long frozenCurrentTimeInMillis = 1749026036595L;
        for (int i = 0; i < 6; i++) {
            long accessTime = frozenCurrentTimeInMillis / 1000;
            if (i < 5) {
                assertTrue(slc.hasAccess(user1, accessTime));
                assertTrue(slc.hasAccess(user2, accessTime));
            } else {
                assertFalse(slc.hasAccess(user1, accessTime));
                assertFalse(slc.hasAccess(user2, accessTime));
            }
        }

        frozenCurrentTimeInMillis = frozenCurrentTimeInMillis + (10_1000); // advance 10 seconds
        for (int i = 0; i < 6; i++) {
            long accessTime = frozenCurrentTimeInMillis / 1000;
            if (i < 5) {
                assertTrue(slc.hasAccess(user1, accessTime));
                assertTrue(slc.hasAccess(user2, accessTime));
            } else {
                assertFalse(slc.hasAccess(user1, accessTime));
                assertFalse(slc.hasAccess(user2, accessTime));
            }
        }
    }

}
