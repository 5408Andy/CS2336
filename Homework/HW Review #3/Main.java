/* 
 * HW Review #3
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/16/2022
 * Class & Section: CS - 2366.003
 */

 // Reading in User Inputs
 import java.util.Scanner;

 // ArrayLists
 import java.util.ArrayList;
 
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
 
             double currentLoadFactor = numberOfElementsFilled / (double)hashTableSize;
             //System.out.println(lamdaValue);
             
             if (currentLoadFactor >=  LOAD_FACTOR) {
                 System.out.println("REHASHING");
                 hashTableSize = (2 * hashTableSize) + 1;
                 
                 
                 
                 int[] hashTableNew = new int[hashTableSize];
                 hashTableNew = initializeToZero(hashTableNew);
                 int indexLeftOff = arrayIndex;
                 
                 return hashingTable(hashTableNew, adjustReceivedKeys(hashTable, receivedKeys, indexLeftOff), hashTableSize);
     
             }
 
             int compressedHash = receivedKeys[arrayIndex] % hashTableSize;
             
             //System.out.println(receivedKeys[arrayIndex]);
 
             if (hashTable[compressedHash] == 0) { // if no collision has occured
 
                 System.out.println("NC(" + receivedKeys[arrayIndex] + " " + compressedHash + ")");
                 hashTable[compressedHash] = receivedKeys[arrayIndex];
 
                 numberOfElementsFilled++;
 
             }
             else {
                   
                 int jumpIndex = 1;
                 while (hashTable[(compressedHash + (jumpIndex * jumpIndex)) % hashTableSize] != 0) {
 
                     jumpIndex++;
 
                 }
                 System.out.println("C(" + receivedKeys[arrayIndex] + " " + (compressedHash + (jumpIndex * jumpIndex)) % hashTableSize + " " + jumpIndex +")");
                 hashTable[(compressedHash + (jumpIndex * jumpIndex)) % hashTableSize] = receivedKeys[arrayIndex];
 
                 numberOfElementsFilled++;
 
             }
             
             //if (numberOfElementsFilled == 6)
                // return hashTable;
 
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
     
  
     public static int[] adjustReceivedKeys(int[] hashTable, int[] receivedKeys, int indexLeftOff) {
        
        ArrayList<Integer> newList = new ArrayList<Integer>();
           
        for (int arrayIndex = 0; arrayIndex < hashTable.length; arrayIndex++) {  
        
          if (hashTable[arrayIndex] != 0) {
           
               newList.add(hashTable[arrayIndex]);
               //System.out.println(hashTable[arrayIndex]);
             
          }
       
        }
        
        for (int arrayIndex = indexLeftOff; arrayIndex < receivedKeys.length; arrayIndex++) { 
         
             newList.add(receivedKeys[arrayIndex]);
             //System.out.println(" "+receivedKeys[arrayIndex]);
                  
        }
        
        for (int arrayIndex = 0; arrayIndex < newList.size(); arrayIndex++) { 
        
             receivedKeys[arrayIndex] = newList.get(arrayIndex);
        
        }
        
        return receivedKeys;
        
     }
     
 }