 // Reading in User Inputs
 import java.util.Scanner;

public class Test {
    
    public static void main(String[] args) {

        Scanner readInput = new Scanner(System.in);

        int readValue = readInput.nextInt();

        System.out.println(readValue + " was inputted.");
        
        int[] arrStorage = new int[10];
        /* 
        for (int arrayIndex = 0; arrayIndex < arrStorage.length; arrayIndex++) {

            arrStorage[arrayIndex] = readInput.nextInt();
            System.out.println(arrStorage[arrayIndex] + " was inputted.");

        }
        */
        arrStorage = fillOutArray(arrStorage);
        readInput.close();

    }

    public static int[] fillOutArray(int[] arrStorage) {

        Scanner readInput = new Scanner(System.in);
        
        for (int arrayIndex = 0; arrayIndex < arrStorage.length; arrayIndex++) {

            arrStorage[arrayIndex] = readInput.nextInt();

        }

        readInput.close();

        return arrStorage;

    }

}
