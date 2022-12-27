public class LinkedList {
    
    Node head;

    public void push(int dataR) {

        Node new_node = new Node(dataR);
        new_node.next = head;
        head = new_node;

    }

    public int peek() {

        if (head == null) { // the stack is empty

            System.out.println("Nothing to peek...");
            

        }

        return head.data;

    }

    public void pop() {

        if (head == null) { // the stack is empty

            System.out.println("Nothing to pop...");

        }
        else {

            head = head.next;

        }
        
    }

    public void printStack() {

        if (head == null) {
            
            System.out.println("Nothing to print...");
            return;

        }

        Node curr = head; 
         
        while (curr != null) { // traverse through the stack until the last element (also known as the last inputted item)

            System.out.println(curr.data);
            curr = curr.next;
            
        }
        

    }

}
