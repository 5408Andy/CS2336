class Node {
    int data;
    Node next;
  
    public Node(int data) {
      this.data = data;
    }
  }
  
  class CircularQueue {
    Node head;
    Node tail;
  
    // Enqueue an item to the queue
    public void enqueue(int data) {
      Node newNode = new Node(data);
      if (head == null) {
        head = newNode;
      } else {
        tail.next = newNode;
      }
      tail = newNode;
      tail.next = head;
    }
  
    // Dequeue an item from the queue
    public int dequeue() {
      if (head == null) {
        return -1;
      }
      int data = head.data;
      if (head == tail) {
        head = null;
        tail = null;
      } else {
        head = head.next;
        tail.next = head;
      }
      return data;
    }
  
    // Print the queue
    public void print() {
      Node current = head;
      while (current != tail) {
        System.out.print(current.data + " ");
        current = current.next;
      }
      System.out.println(current.data);
    }
  }
  
