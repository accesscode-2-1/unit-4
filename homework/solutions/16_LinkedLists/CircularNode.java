package nyc.c4q.jrod.current;

public class CircularNode {
  CircularNode next;
  String data;

  public CircularNode(String data) {
    this.data = data;
  }
}

class CircularList {
  CircularNode head;

  public void addToHead(String data) {
    if (isEmpty()) {
      addToEmptyList(data);
      return;
    }

    CircularNode newNode = new CircularNode(data);
    CircularNode lastNode = findLastNode();
    lastNode.next = newNode;
    newNode.next = head;
    head = newNode;
  }

  public void addToTail(String data) {
    if (isEmpty()) {
      addToEmptyList(data);
      return;
    }

    CircularNode newNode = new CircularNode(data);
    CircularNode lastNode = findLastNode();
    lastNode.next = newNode;
    newNode.next = head;
  }

  public boolean delete(String data) {
    if (isEmpty()) return false;

    if (head.data.equals(data)) {
      CircularNode lastNode = findLastNode();
      lastNode.next = head.next;
      head = head.next;
      return true;
    }

    // else find the node that matches (currNode)
    // keep a trailing reference to remove currNode, if found
    CircularNode prevNode = head;
    CircularNode currNode = head.next;

    while (currNode != head) {
      if (currNode.data.equals(data)) {
        // deleted!
        prevNode.next = currNode.next;
        return true;
      }

      // move references up one node
      prevNode = currNode;
      currNode = currNode.next;
    }

    return false;
  }

  public boolean insertAfter(String prev, String data) {
    // can't insert 'after' if empty list
    if (isEmpty()) return false;

    // using a do-while loop avoids the need to have a separate
    // check for head vs non-head cases
    CircularNode currNode = head;
    do {
      if (currNode.data.equals(prev)) {
        CircularNode newNode = new CircularNode(data);
        newNode.next = currNode.next;
        currNode.next = newNode;
        return true;
      }

      currNode = currNode.next;
    }
    while (currNode != head);

    return false;
  }

  private boolean isEmpty() {
    return head == null;
  }

  private void addToEmptyList(String data) {
    CircularNode newNode = new CircularNode(data);
    head = newNode;
    newNode.next = head;
  }

  private CircularNode findLastNode() {
    CircularNode currNode = head;
    while (currNode.next != head) {
      currNode = currNode.next;
    }
    return currNode;
  }

  public void print() {
    if (!isEmpty()) {
      CircularNode current = head;
      do {
        System.out.print(current.data + ",");
        current = current.next;
      }
      while (current != head);
    }

    System.out.println();
  }
}

class CircularMain {
  public static void main(String[] args) {
    CircularList list = new CircularList();
    list.print();
    list.addToHead("1");
    list.print();
    list.addToHead("2");
    list.print();
    list.addToTail("3");
    list.print();
    list.addToTail("4");
    list.print();
    list.addToTail("5");
    list.print();
    list.delete("2");
    list.print();
    list.delete("5");
    list.print();
    list.delete("3");
    list.print();
    list.insertAfter("1", "9");
    list.print();
    list.insertAfter("4", "0");
    list.print();
    list.insertAfter("9", "5");
    list.print();
    list.insertAfter("0", "3");
    list.print();
  }
}