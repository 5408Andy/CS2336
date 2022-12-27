public class Main {

    public static void main(String[] args) {

        CircularQueue queue = new CircularQueue();

        // Enqueue some items
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        


        // Print the queue
        queue.print();
        // Output: 1 2 3

        // Dequeue an item
        int item = queue.dequeue();
        System.out.println("Dequeued item: " + item);
        // Output: Dequeued item: 1

        // Print the queue again
        queue.print();
        // Output: 2 3


    }

}