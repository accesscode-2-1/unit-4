package nyc.c4q.jrod.current.recursion;

public class PrintNumbers {
  public static void main(String[] args) {
    printIteratively();
    printTailRecursively(1);
    printHeadRecursively(10);
  }

  private static void printHeadRecursively(int i) {
    if(i == 0) return;

    printHeadRecursively(i - 1);

    doSomething(i);
  }

  private static void printTailRecursively(int i) {
    doSomething(i);

    if(i == 10) return;

    printTailRecursively(i + 1);
  }

  private static void doSomething(int i) {
    System.out.println(i);
  }

  private static void printIteratively() {
    for (int i = 1; i <= 10; i++) {
      doSomething(i);
    }
  }
}
