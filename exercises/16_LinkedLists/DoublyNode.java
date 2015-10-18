package nyc.c4q.jrod.list;

public class DoublyNode {
  DoublyNode prev;
  DoublyNode next;
  String data;
  public DoublyNode(String data) { this.data = data;}
}

class DoublyList {
  DoublyNode head;
  DoublyNode tail;

  public void addToHead(String data) {
    DoublyNode newNode = new DoublyNode(data);
    newNode.next = head;
    if(head != null) {
      head.prev = newNode;
    }
    else {
      tail = newNode;
    }
    head = newNode;
  }

  public void addToTail(String data) {
    DoublyNode newNode = new DoublyNode(data);

    newNode.prev = tail;
    if(tail != null) {
      tail.next = newNode;
    }
    else {
      head = newNode;
    }
    tail = newNode;
  }

  public void delete(String data) {
    DoublyNode current = head;

    while(current != null && !current.data.equals(data)) {
      current = current.next;
    }

    if(current != null) {
      if(current == head) {
        head = head.next;
      }
      else {
        current.prev.next = current.next;
      }

      if(current == tail) {
        tail = tail.prev;
      }
      else {
        current.next.prev = current.prev;
      }
//      current.prev = null;
//      current.next = null;
    }
  }

  public void print() {
    DoublyNode current = head;
    while(current != null){
      System.out.print(current.data + ",");
      current = current.next;
    }
    System.out.println();
  }
}

class DoublyMain {
  public static void main(String[] args) {
//    DoublyList list = new DoublyList();
//    list.print();
//    list.addToHead("test1");
//    list.print();
//    list.addToHead("test2");
//    list.print();
    DoublyList list2 = new DoublyList();
    list2.addToTail("foo");
    list2.print();
    list2.addToTail("bar");
    list2.print();
  }
}
