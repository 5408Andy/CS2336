/* 
 * In Class Assignment #3
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/5/2022
 * Class & Section: CS - 2366.003
 */

public class Main {

    enum Precedence {
        
    }

    public static void main (String[] args) {

        StackLinkList stackTest = new StackLinkList();

        stackTest.push("Andy");
        stackTest.push("Alan");
        stackTest.push("Thom");
        stackTest.push("Danny");
        stackTest.push("Mitchell");

        stackTest.printAllNodes();

        stackTest.pop();

        System.out.println();

        stackTest.printAllNodes();

        System.out.println();

        System.out.println(stackTest.checkHeadNode());

        String inFix = "5+6(2+8)-1";

        String postFix = "";

        for (int stringIndex = 0; stringIndex < inFix.length(); stringIndex++) {

            if (Character.isDigit(inFix.charAt(stringIndex)) == true) {

                postFix += inFix.charAt(stringIndex);

            }
            else {

                stackTest.push(Character.toString(inFix.charAt(stringIndex)));

            }

        }



    }

}