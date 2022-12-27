public class Main {

    public static final int LOCKER_SIZE = 100;
    public static void main(String[] args) {

        boolean[] lockerArr = new boolean[LOCKER_SIZE]; // true is opened locker, false is closed locker

        for (int arrIndex = 0; arrIndex< LOCKER_SIZE; arrIndex++) {

            lockerArr[arrIndex] = true;

        } // S1 opens all the lockers

        for (int arrIndex = 1; arrIndex< LOCKER_SIZE; arrIndex = arrIndex + 2) {

            lockerArr[arrIndex] = false;

        } // S2 opens every other locker starting at the 2nd locker

        for(int i = 2; i < LOCKER_SIZE; i++) {

            for(int j = 0 + i; j < LOCKER_SIZE; j = j + i + 1) {

                if (lockerArr[j] == false) {

                    lockerArr[j] = true;
    
                }
                else {
    
                    lockerArr[j] = false;
    
                }

            }

        }

        printLockers(lockerArr);
        /* 
        for (int arrIndex = 3; arrIndex< LOCKER_SIZE; arrIndex = arrIndex + 3) {

            if (lockerArr[arrIndex] == false) {

                lockerArr[arrIndex] = true;

            }
            else {

                lockerArr[arrIndex] = false;

            }
            
        } // S3 opens every other locker starting at the 3rd locker

        printLockers(lockerArr);

        for (int arrIndex = 4; arrIndex< LOCKER_SIZE; arrIndex = arrIndex + 4) {

            if (lockerArr[arrIndex] == false) {

                lockerArr[arrIndex] = true;

            }
            else {

                lockerArr[arrIndex] = false;

            }
            
        } // S3 opens every other locker starting at the 3rd locker

        printLockers(lockerArr);
        */
        
    }

    public static void printLockers(boolean[] lockerArr) {

        for (int arrIndex = 0; arrIndex< LOCKER_SIZE; arrIndex++) {

            if (lockerArr[arrIndex] == true)
                System.out.println("L" + (arrIndex + 1) + " " + lockerArr[arrIndex] + ", ");

        } // display the locker array

        System.out.print("\n");

    }
    
}