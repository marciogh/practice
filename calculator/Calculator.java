package calculator;

import java.util.Deque;
import java.util.LinkedList;

public class Calculator {

    public int calculate(String s) throws IllegalArgumentException {
        String digits = "0123456789";
        char space = ' ';
        char sum = '+';
        char sub = '-';
        char openBracket = '(';
        char closeBracket = ')';

        Deque<Integer> digitStack = new LinkedList<>();
        Deque<Integer> operandStack = new LinkedList<>();
        Deque<Character> operatorStack = new LinkedList<>();
        Deque<Integer> operationStack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (digits.indexOf(c) >= 0) {
                digitStack.add(Character.getNumericValue(c));
            } else {
                if (!digitStack.isEmpty()) {
                    int operand = 0;
                    while (!digitStack.isEmpty()) {
                        operand += digitStack.pop() * (int) Math.pow(10, digitStack.size());
                    }
                    operandStack.add(operand);
                }
            }

            if ((c == sum || c == sub) && operandStack.size() == 0) {
                throw new IllegalArgumentException("expecting operand at " + i);
            } else {
                if (c == sum || c == sub) {
                    operatorStack.add(c);
                }
            }

            if (operandStack.size() == 2 && operatorStack.size() == 1) {
                char operation = operatorStack.pop();
                int first = operandStack.pollFirst();
                int second = operandStack.pollFirst();
                if (operation == sum) {
                    return first + second;
                }
                if (operation == sub) {
                    return first - second;
                }
            }

        }

        return 0;
    }

    public int calculateR(int[] operands) {
        if (operands.length == 1) {
            return operands[0];
        }

        int[] newOperands = new int[operands.length - 1];
        System.arraycopy(operands, 0, newOperands, 0, operands.length - 1);
        return operands[operands.length - 1] + calculateR(newOperands);
    }

    public int[] exprToArray(String s) {
        String digits = "0123456789";
        char space = ' ';
        char sum = '+';
        char sub = '-';
        char signal = sum;

        Deque<Integer> digitStack = new LinkedList<>();
        Deque<Integer> operandStack = new LinkedList<>();

        for (int i = 0; i <= s.length(); i++) {
            char c = space;
            if (i < s.length()) {
                c = s.charAt(i);
            }
            if ((c == sum || c == sub) && digitStack.size() > 0) {
                throw new IllegalArgumentException("unexpected operator at " + i);
            } else {
                if (c == sum || c == sub) {
                    signal = c;
                }
            }

            if (digits.indexOf(c) >= 0) {
                digitStack.add(Character.getNumericValue(c));
            } else {
                if (!digitStack.isEmpty()) {
                    int operand = 0;
                    while (!digitStack.isEmpty()) {
                        operand += digitStack.pop() * (int) Math.pow(10, digitStack.size());
                    }
                    if (signal == sub) {
                        operand *= -1;
                    }
                    operandStack.add(operand);
                }
            }
        }
        return operandStack.stream().mapToInt(Integer::intValue).toArray();
    }

    public boolean parenthesis(String r) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < r.length(); i++) {
            Character c = r.charAt(i);
            if (c == '(') {
                stack.add(c);
            }
            if (c == ')' && stack.pop() != '(') {
                return false;
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public String paranthesisR(String str, StringBuffer acc) {
        int open = str.indexOf('(');
        if (open >= 0) {
            int close = str.substring(open).indexOf(')');
            String left = str.substring(1, open + 2);
            String middle = str.substring(open + 2, close + 1);
            String right = str.substring(close + 2, str.length());
            acc.append(paranthesisR(middle, acc));
            acc.append(paranthesisR(left, acc));
            acc.append(paranthesisR(right, acc));
        }
        return str;
    }

    public static void main(String[] args) {

        Calculator c = new Calculator();
        String r = "(a)(bc)d";
        StringBuffer acc = new StringBuffer();
        c.paranthesisR(r, acc);
        System.out.println(acc.toString());

        /*
         * Calculator c = new Calculator();
         * String expr = "10 + 1 + 1 - 1 + 2000 -5";
         * int[] operands = c.exprToArray(expr);
         * 
         * try {
         * int result = c.calculateR(operands);
         * System.out.println(result);
         * } catch (Exception e) {
         * System.out.println(e.getMessage());
         * }
         */

    }

}
