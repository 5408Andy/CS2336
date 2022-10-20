public class Main {
    
    public static void main(String[] args) {

        int dataValue = 10;

        LinkedList listMain = new LinkedList(new Node(dataValue));

        for (int i = 0; i < 9; i++) {

            dataValue += 10;

            listMain.insertNode(new Node(dataValue));

        }

        // inserts node at the beginning of the linked list

        //listMain.removeNode(0);

        //listMain.removeNode(100);

        //listMain.removeNode(50);

 //       listMain.removeNode(80);

   //     listMain.removeNode(90);

        listMain.insertNodeAtStart(new Node(1000));

        listMain.insertNode(new Node(25));

        listMain.removeNode(10);

        //listMain.printList(listMain.getHeadNode());

        listMain.sortNode();

        System.out.println();

        if (listMain.checkListSorted() == true) {

            System.out.println("The list is sorted!\n");

        }
        else {

            System.out.println("The list is NOT sorted\n");

        }

        try {

            listMain.printList(listMain.getHeadNode());

        }
        catch(NullPointerException E) {

            System.out.println(E.getMessage());

        }

    }

}
