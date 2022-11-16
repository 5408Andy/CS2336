/* 
 * HW Review #3
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/16/2022
 * Class & Section: CS - 2366.003
 */

 // Reading in User Inputs
import java.util.Scanner;

 public class Main {

    public static final double LOAD_FACTOR = 0.5;
    public static void main(String[] args) {

        // hash table size
        int hashTableSize = 11;

        // hash table where values will be organized
        int[] hashTable = new int[hashTableSize];
        hashTable = initializeToZero(hashTable);

        // values being sent to be hashed out
        Scanner readInput = new Scanner(System.in);
        
        System.out.println("Enter number of values");
        int keyArraySize = readInput.nextInt();
        System.out.println(keyArraySize + " is the size");
        
        int[] receivedKeys = new int[keyArraySize];
        
        for (int arrayIndex = 0; arrayIndex < receivedKeys.length; arrayIndex++) {
            
            System.out.println("Enter value...");
            int readValue = readInput.nextInt();
            System.out.println(readValue + " inputted");
            receivedKeys[arrayIndex] = readValue;

        }

        readInput.close();

        // hash table with hashed out values
        int[] hashedTable; 
        hashedTable = hashingTable(hashTable, receivedKeys, hashTableSize);

        displayHashTable(hashedTable);

    } // Main

    public static int[] hashingTable(int[] hashTable, int[] receivedKeys, int hashTableSize) {

        int numberOfElementsFilled = 0;

        for (int arrayIndex = 0; arrayIndex < receivedKeys.length; arrayIndex++) {

            double lamdaValue = numberOfElementsFilled / (double)hashTableSize;

            if (lamdaValue >=  LOAD_FACTOR) {

                System.out.println("Hash Table Rehashed");
    
                hashTableSize = (2 * hashTableSize) + 1;
                
                int[] hashTableNew = new int[hashTableSize];
                hashTableNew = initializeToZero(hashTableNew);

                return hashingTable(hashTableNew, receivedKeys,hashTableSize);
    
            }

            int compressedHash = receivedKeys[arrayIndex] % hashTableSize;
            
            //System.out.println(receivedKeys[arrayIndex]);

            if (hashTable[compressedHash] == 0) { // if no collision has occured

                System.out.println("(" + receivedKeys[arrayIndex] + " " + compressedHash + ")");
                hashTable[compressedHash] = receivedKeys[arrayIndex];

                numberOfElementsFilled++;

            }
            else {

                int jumpIndex = 0;
                while (hashTable[(compressedHash + (jumpIndex * jumpIndex)) % hashTableSize] != 0) {

                    jumpIndex++;

                }
                //System.out.println("(" + receivedKeys[arrayIndex] + " " + (compressedHash + (jumpIndex * jumpIndex)) % hashTableSize + ")");
                hashTable[(compressedHash + (jumpIndex * jumpIndex)) % hashTableSize] = receivedKeys[arrayIndex];

                numberOfElementsFilled++;

            }

        }

        return hashTable;

    } // hashingTable

    public static void displayHashTable(int[] hashTableReceived) {

        for (int arrayIndex = 0; arrayIndex < hashTableReceived.length; arrayIndex++) {

            if (hashTableReceived[arrayIndex] != 0) { // if there is a key
                
                System.out.print(hashTableReceived[arrayIndex] + " ");

            }
            else { // if there is no key

                System.out.print("_ ");

            }

        }

    } // displayHashTable

    public static int[] initializeToZero(int[] hashTableReceived) {

        // loops through each index of the array and sets it to zero
        for (int arrayIndex = 0; arrayIndex < hashTableReceived.length; arrayIndex++) {

            hashTableReceived[arrayIndex] = 0;

        }

        return hashTableReceived;

    } // initializeToZero

 }