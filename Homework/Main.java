/* 
 * HW Review #1
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/9/2022
 * Class & Section: CS - 2366.003
 */

import java.util.ArrayList;

public class Main {
    
    private static final ArrayList<String> BINARY_TEST = new ArrayList<String>() { // short hand version of the leader stats to help with processing
            
        {

            add("1111010101");
            add("1011111112");
            add("This is not a binary value");
            add("456ASD5?>@!");
            add("10120121648");
            add("1110011010");
        
        }

    };

    private static final ArrayList<Integer> INTEGER_TEST = new ArrayList<Integer>() { // short hand version of the leader stats to help with processing
            
        {

            add(525);
            add(325231);
            add(-562514);
            add(6662);
            add(7);
            add(16);
        
        }

    };

    private static final ArrayList<Double> DOUBLE_TEST = new ArrayList<Double>() { // short hand version of the leader stats to help with processing
            
        {

            add(32.0562);
            add(-1.067);
            add(-562514.0);
            add(100562.2);
            add(4984.398);
            add(-0.54651);
        
        }

    };

    private static final ArrayList<String> STRING_TEST = new ArrayList<String>() { // short hand version of the leader stats to help with processing
            
        {

            add("THIS IS A STRING");
            add("ANDY DANG NGUYEN");
            add("computer Science is cool!");
            add("HeLLo! What is Up?");
            add("BLAH blah BlAh");
            add("...");
        
        }

    };

    public static void main(String[] args) {

        // string to binary decimal value 
        for (int stringIndex = 0; stringIndex < BINARY_TEST.size(); stringIndex++) { // loops through provided test cases

            try {

                System.out.println("This string could be converted into binary: " + bin2Dec(BINARY_TEST.get(stringIndex)));

            }
            catch (BinaryFormatException E) { // if user defined exception is thrown, print out the getMessage method

                System.out.println(E.getMessage());

            }

        }

        

        // generic lists
        GenericList<Integer> integerList = new GenericList<Integer>(INTEGER_TEST);
        GenericList<Double> doubleList = new GenericList<Double>(DOUBLE_TEST);
        GenericList<String> stringList = new GenericList<String>(STRING_TEST);

        // INTEGER TESTING
        System.out.println("\nINTEGER GENERIC LIST");

        System.out.println("Testing out getList() method for the INTEGER LIST: " + integerList.getList());
        
        integerList.setList(396073);
        System.out.println("Used setList() method to add \'396073\' to the INTEGER LIST: " + integerList.getList());

        integerList.InsertionSort();
        System.out.println("Used InsertionSort() method to sort the INTEGER LIST from least to greatest: " + integerList.getList());

        String valueFound1 = (integerList.BinarySearch(396073)) ? "\'396073\' was found within the INTEGER LIST using BinarySearch() method" : "\'396073\' was NOT found within the INTEGER LIST using BinarySearch() method";
        System.out.println(valueFound1);

        String valueFound2 = (integerList.BinarySearch(0)) ? "\'0\' was found within the INTEGER LIST using BinarySearch() method" : "\'0\' was NOT found within the INTEGER LIST using BinarySearch() method";
        System.out.println(valueFound2);

        // DOUBLE TESTING
        System.out.println("\nDOUBLE GENERIC LIST");

        System.out.println("Testing out getList() method for the DOUBLE LIST: " + doubleList.getList());
        
        doubleList.setList(396073.123);
        System.out.println("Used setList() method to add \'396073\' to the DOUBLE LIST: " + doubleList.getList());

        doubleList.InsertionSort();
        System.out.println("Used InsertionSort() method to sort the INTEGER LIST from least to greatest: " + doubleList.getList());

        String valueFound3 = (doubleList.BinarySearch(396073.123)) ? "\'396073.123\' was found within the DOUBLE LIST using BinarySearch() method" : "\'396073.123\' was NOT found within the DOUBLE LIST using BinarySearch() method";
        System.out.println(valueFound3);

        String valueFound4 = (doubleList.BinarySearch(0.0)) ? "\'0.0\' was found within the DOUBLE LIST using BinarySearch() method" : "\'0.0\' was NOT found within the DOUBLE LIST using BinarySearch() method";
        System.out.println(valueFound4);

        // STRING TESTING
        System.out.println("\nSTRING GENERIC LIST");

        System.out.println("Testing out getList() method for the STRING LIST: " + stringList.getList());
        
        stringList.setList("396073.string");
        System.out.println("Used setList() method to add \'396073.string\' to the STRING LIST: " + stringList.getList());

        stringList.InsertionSort();
        System.out.println("Used InsertionSort() method to sort the STRING LIST from least to greatest: " + stringList.getList());

        String valueFound5 = (stringList.BinarySearch("396073.string")) ? "\'396073.string\' was found within the STRING LIST using BinarySearch() method" : "\'396073.string\' was NOT found within the STRING LIST using BinarySearch() method";
        System.out.println(valueFound5);

        String valueFound6 = (stringList.BinarySearch("0.0")) ? "\'0.0\' was found within the STRING LIST using BinarySearch() method" : "\'0.0\' was NOT found within the STRING LIST using BinarySearch() method";
        System.out.println(valueFound6);

    }

    public static int bin2Dec(String binaryStringReceived) throws BinaryFormatException {

        int decimalBinaryValue = -1;

        for (int stringIndex = 0; stringIndex < binaryStringReceived.length(); stringIndex++) { // loops through characters in string

            if (binaryStringReceived.charAt(stringIndex) != '0' && binaryStringReceived.charAt(stringIndex) != '1') { // if the value at index is not either 1 or 0, throw an exception

                throw new BinaryFormatException(binaryStringReceived);

            }

        }
        
        decimalBinaryValue = Integer.parseInt(binaryStringReceived); // turn the string into an integer

        return decimalBinaryValue;

    } // bin2Dec

} 
