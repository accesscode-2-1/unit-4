package nyc.c4q.jrod.current.tree;

import java.util.Scanner;
import java.util.Stack;

public class FunWithTrees {
  public static void main(String[] args) {
    // This section deals with forming an expression tree from an expression

    String expression = "a b + c d e + * *";
    System.out.println("expression: " + expression);

    Tree expressionTree = formExpressionTree(expression);

    System.out.print("postfix: ");
    expressionTree.printPostfix();

    System.out.print("prefix: ");
    expressionTree.printPrefix();

    System.out.print("infix: ");
    expressionTree.printInfix();

    System.out.print("breadth: ");
    expressionTree.printBreadth();

    // This section deals with Binary Search Trees (BSTs)

    Tree BST = makeBST();
    System.out.println("does exist?: " + BST.exists(7));
    System.out.println("min?: " + BST.findMin());
    System.out.println("max?: " + BST.findMax());
//    BST.insert(1);
//    BST.insert(13);
//    System.out.println("min?: " + BST.findMin()); // should be 1 after insert(1)
//    System.out.println("max?: " + BST.findMax()); // should be 13 after insert(13)
  }

  private static Tree makeBST() {
    Node ten = new Node("10");
    Node five = new Node("5");
    Node twelve = new Node("12");
    Node two = new Node("2");
    Node six = new Node("6");
    Node three = new Node("3");
    Node four = new Node("4");

    three.right = four;
    two.right = three;
    five.left = two;
    five.right = six;
    ten.left = five;
    ten.right = twelve;

    Tree tree = new Tree();
    tree.root = ten;
    return tree;
  }

  private static Tree formExpressionTree(String expression) {
    Scanner input = new Scanner(expression);
    Stack<Node> stack = new Stack<>();

    while (input.hasNext()) {
      String symbol = input.next();
      Node node = new Node(symbol);

      if (isOperator(symbol)) {
        node.right = stack.pop();
        node.left = stack.pop();
      }

      stack.push(node);
    }

    if (stack.size() != 1) {
      throw new IllegalStateException("invalid stack");
    }

    Node rootNode = stack.pop();

    Tree tree = new Tree();
    tree.root = rootNode;
    return tree;
  }

  private static boolean isOperator(String symbol) {
    return "+-*/".contains(symbol);
  }
}

