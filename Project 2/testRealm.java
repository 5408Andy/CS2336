public class testRealm {

    public static void main (String[] args) {

        /* 
       String strArr = "Tom";
       String strArr2 = "Jane";

        int strCompare = strArr.compareTo(strArr2);

        if (strCompare > 0) {

            System.out.println(strArr + strCompare+ " greater than " + strArr2);

        }
        else if (strCompare < 0) {

            System.out.println(strArr2 + " greater than " + strArr);

        }
        else {

            System.out.println("Equal");

        }
        */

        String strArr[] = {"Tom","Jane","Bob","Ada"};

        sortLexicographically(strArr);

        printArray(strArr);
    }

    public static void sortLexicographically(String strArr[]) {
        
        for (int i = 0; i < strArr.length; i++) {
            for (int j = i + 1; j < strArr.length; j++) {
                if (strArr[i].compareToIgnoreCase(strArr[j])
                    > 0) {
                    String temp = strArr[i];
                    strArr[i] = strArr[j];
                    strArr[j] = temp;
                }
            }
        }

    }

    public static void printArray(String strArr[])
    {
        for (String string : strArr)
            System.out.print(string + " ");
        System.out.println();
    }

}