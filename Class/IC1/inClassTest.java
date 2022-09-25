public class inClassTest {
    public static void main (String[] args) {
        int count = 0;
        
        int[][] myArray = { {0,1,2}, 
                            {3,4,5}, 
                            {6,7,8}};
        for (int creatureColumnIndex = 0; creatureColumnIndex < 3; creatureColumnIndex++) { // reads by column by column

            for (int creatureRowIndex = 0; creatureRowIndex < 3; creatureRowIndex++) {

                System.out.print(myArray[creatureRowIndex][creatureColumnIndex]);
                

            }

        }

    }

}
