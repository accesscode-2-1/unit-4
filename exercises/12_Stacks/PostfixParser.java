package nyc.c4q.jrod;

import java.util.Scanner;
import java.util.Stack;

public class PostfixParser {
  static String operators = "+-/*";

  public static void main(String[] args) {
    String expression = "3 5 4 + *";
    printAndEvaluatePostfix(expression);

    expression = "3 5 + 4 *";
    printAndEvaluatePostfix(expression);

    expression = "3 5 - 4 *";
    printAndEvaluatePostfix(expression);
  }

  private static void printAndEvaluatePostfix(String expression) {
    System.out.println(expression);
    System.out.println(evaluatePostfixExpression(expression));
    System.out.println();
  }

  private static int evaluatePostfixExpression(String expression) {
    Stack<Integer> operandStack = new Stack();

    Scanner input = new Scanner(expression);
    while(input.hasNext()) {
      String token = input.next();

      if(isOperator(token)) {
        int rightOperand = operandStack.pop();
        int leftOperand = operandStack.pop();

        int result = performOperation(leftOperand, rightOperand, token);
        operandStack.push(result);
      }
      else {
        int operand = Integer.parseInt(token);
        operandStack.push(operand);
      }
    }

    if(operandStack.size() != 1) {
      throw new IllegalStateException("unexpected stack state");
    }

    return operandStack.pop();
  }

  private static int performOperation(int left, int right, String op) {
    if(op.equals("+")) {
      return left + right;
    }
    else if(op.equals("-")) {
      return left - right;
    }
    else if(op.equals("*")) {
      return left * right;
    }
    else if(op.equals("/")) {
      return left / right;
    }
    else {
      throw new IllegalArgumentException("not a valid operator");
    }
  }

  private static boolean isOperator(String token) {
    return token.length() == 1 && operators.contains(token);
  }
}
