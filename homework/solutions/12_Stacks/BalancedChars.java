package nyc.c4q.jrod;

import java.util.Stack;

public class BalancedChars {
  public static void main(String[] args) {
    testBalancedChars("");
    testBalancedChars("([{}])");
    testBalancedChars("{[}]");
    testBalancedChars("(([])))");
    testBalancedChars("()[{}]");
    testBalancedChars("((())");
  }

  private static void testBalancedChars(String s) {
    System.out.printf("is '%s' balanced?: %b\n", s, isBalanced(s));
  }

  private static boolean isBalanced(String s) {
    if(s.isEmpty()) return true;

    Stack<Character> stack = new Stack<>();

    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if(isLeftChar(c)) {
        stack.push(c);
      }
      else if(isRightChar(c)){
        if(stack.isEmpty()) {
          return false;
        }
        char leftChar = stack.pop();
        if(!isMatchingPair(leftChar, c)) {
          return false;
        }
      }
      else {
        return false; // invalid char
      }
    }

    // no failures up to now
    // but first make sure we don't have extra left chars
    return stack.isEmpty();
  }

  private static boolean isMatchingPair(char lc, char rc) {
    return (lc == '{' && rc == '}') ||
            (lc == '(' && rc == ')') ||
            (lc == '[' && rc == ']');
  }

  private static boolean isRightChar(char c) {
    return c == '}' || c == ')' || c == ']';
  }

  private static boolean isLeftChar(char c) {
    return c == '{' || c == '(' || c == '[';
  }
}
