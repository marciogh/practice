package ratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * You are tasked with implementing a rate limiter method in Kotlin.
 * The rate limiter should allow up to 5 requests per second per user.
 *
 * The userId parameter is a unique identifier for each user,
 * and the method should return true if the user is allowed to make a request,
 * and false otherwise.
 * If the method returns true this implies that the resource was accessed.
 * 
 * You may assume that the userId parameter is a valid UUID, and you may use any
 * standard library functions or classes available in Kotlin.
 *
 * To complete this task, please implement the userHasAccess method.
 * You may also include any helper methods or classes that you think are
 * necessary to complete the task.
 */

public class Code {

    Map<UUID, UserAccess> userCounter = new HashMap<>();

    public Boolean userHasAccess(UUID userId, long currentTime) {
        UserAccess userAccess = userCounter.computeIfAbsent(userId, (_) -> new UserAccess(currentTime, 0));
        if (currentTime - userAccess.time >= 1000) {
            userAccess.time = currentTime;
            userAccess.counter = 1;
        } else {
            userAccess.counter++;
        }
        return userAccess.counter <= 5;
    }

    public static void main(String[] args) {
        test6();
    }

    public static void test1() {
        Code c = new Code();
        UUID user1 = UUID.randomUUID();
        boolean access = false;
        for (int i = 1; i < 10; i++) {
            access = c.userHasAccess(user1, 1000 + (i * 1001));
        }
        System.out.println(access);
    }

    public static void test2() {
        Code c = new Code();
        UUID user1 = UUID.randomUUID();
        boolean access = false;
        for (int i = 1; i < 10; i++) {
            access = c.userHasAccess(user1, 1000 + i);
        }
        System.out.println(access);
    }

    public static void test3() {
        Code c = new Code();
        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();
        boolean access1 = false;
        boolean access2 = false;
        for (int i = 1; i < 10; i++) {
            access1 = c.userHasAccess(user1, 1000 + i);
            access2 = c.userHasAccess(user2, 1000 + i);
        }
        System.out.println(access1);
        System.out.println(access2);
    }

    public static void test4() {
        Code c = new Code();
        UUID user1 = UUID.randomUUID();
        UUID user2 = UUID.randomUUID();
        boolean access1 = false;
        boolean access2 = false;
        for (int i = 1; i < 10; i++) {
            access1 = c.userHasAccess(user1, 1000 + i);
            access2 = c.userHasAccess(user2, 1000 + (i * 1000));
        }
        System.out.println(access1);
        System.out.println(access2);
    }

    public static void test5() {
        Code c = new Code();
        UUID user1 = UUID.randomUUID();
        boolean access1 = false;
        for (int i = 1; i < 10; i++) {
            access1 = c.userHasAccess(user1, 1000 + (i * 1000));
        }
        System.out.println(access1);
    }

    public static void test6() {
        Code c = new Code();
        UUID user1 = UUID.randomUUID();
        boolean access1 = false;
        for (int i = 0; i < 6; i++) {
            access1 = c.userHasAccess(user1, 1000);
            System.out.println(access1);
        }
    }
}
