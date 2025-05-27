package reorderlogs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class ReoderLogs {

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isContentNumeric(String content) {
        for (String s : content.split(" ")) {
            if (!isNumeric(s)) {
                return false;
            }
        }
        return true;
    }

    public String[] reorderLogFiles(String[] logs) {
        if (logs.length == 0) {
            return logs;
        }
        Comparator<String> c = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1.substring(s1.indexOf(' ') + 1);
                String c2 = s2.substring(s2.indexOf(' ') + 1);
                if (!isContentNumeric(c1) && isContentNumeric(c2)) {
                    return -1;
                }
                if (isContentNumeric(c1) && !isContentNumeric(c2)) {
                    return +1;
                }
                if (!isContentNumeric(c1) && !isContentNumeric(c2)) {
                    if (c1.equals(c2)) {
                        return s1.compareTo(s2);
                    }
                    return c1.compareTo(c2);
                }
                return 0;
            }
        };
        Arrays.sort(logs, c);
        return logs;
    }

    /*
     * TreeMap<String, List<String>> letterLogs = new TreeMap<>();
     * HashMap<String, List<String>> digiLogs = new LinkedHashMap<>();
     * for (int i = 0; i < logs.length; i ++) {
     * String identifier = logs[i].substring(0, logs[i].indexOf(' '));
     * String content = logs[i].substring(logs[i].indexOf(' ') + 1);
     * if (isContentNumeric(content)) {
     * List<String> contents = digiLogs.getOrDefault(identifier, new
     * ArrayList<String>());
     * contents.add(content);
     * digiLogs.put(identifier, contents);
     * } else {
     * List<String> identifiers = letterLogs.getOrDefault(content, new
     * ArrayList<String>());
     * identifiers.add(identifier);
     * letterLogs.put(content, identifiers);
     * }
     * }
     * List<String> results = new ArrayList<>();
     * for (String key : letterLogs.keySet()) {
     * Collections.sort(letterLogs.get(key));
     * for (String identifier: letterLogs.get(key)) {
     * results.add(identifier + " " + key);
     * }
     * }
     * for (String key : digiLogs.keySet()) {
     * for (String content: digiLogs.get(key)) {
     * results.add(key + " " + content);
     * }
     * 
     * }
     * return results.toArray(new String[results.size()]);
     */

    public static void main(String[] args) {
        ReoderLogs r = new ReoderLogs();
        String[] result = (r.reorderLogFiles(new String[] {
                "a1 9 2 3 1",
                "g1 act car",
                "zo4 4 7",
                "ab1 off key dog",
                "a8 act zoo",
                "a2 act car",
        }));
        System.out.println(result);
    }

    @Test
    public void test() {
        ReoderLogs r = new ReoderLogs();

        assertEquals(0, r.reorderLogFiles(new String[] {}).length);

        String[] l1 = new String[] {
                "let1 art can",
                "let3 art zero",
                "let2 own kit dig",
                "dig1 8 1 5 1",
                "dig2 3 6",
        };
        String[] l2 = r.reorderLogFiles(
                new String[] {
                        "dig1 8 1 5 1",
                        "let1 art can",
                        "dig2 3 6",
                        "let2 own kit dig",
                        "let3 art zero",
                });
        assertArrayEquals(
                l1,
                l2);

        assertArrayEquals(
                new String[] {
                        "1 n u",
                        "r 527",
                        "j 893",
                        "6 14",
                        "6 82",
                },
                r.reorderLogFiles(
                        new String[] {
                                "1 n u",
                                "r 527",
                                "j 893",
                                "6 14",
                                "6 82",
                        }));

        assertArrayEquals(
                new String[] {
                        "a2 act car",
                        "g1 act car",
                        "a8 act zoo",
                        "ab1 off key dog",
                        "a1 9 2 3 1",
                        "zo4 4 7",
                },
                r.reorderLogFiles(
                        new String[] {
                                "a1 9 2 3 1",
                                "g1 act car",
                                "zo4 4 7",
                                "ab1 off key dog",
                                "a8 act zoo",
                                "a2 act car",
                        }));
    }

    @Test
    public void testComparator() {
        Comparator<String> c = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1.substring(s1.indexOf(' ') + 1);
                String c2 = s2.substring(s2.indexOf(' ') + 1);
                if (!isContentNumeric(c1) && isContentNumeric(c2)) {
                    return -1;
                }
                if (isContentNumeric(c1) && !isContentNumeric(c2)) {
                    return +1;
                }
                if (!isContentNumeric(c1) && !isContentNumeric(c2)) {
                    return s1.compareTo(s2);
                }
                return 0;
            }
        };
        String[] logs = new String[] {
                "abc dad",
                "cde cda",
                "kkk 123",
                "abc asd"
        };
        Arrays.sort(logs, c);
        assertArrayEquals(
                new String[] {
                        "abc asd",
                        "abc dad",
                        "cde cda",
                        "kkk 123"
                },
                logs);
    }

}
