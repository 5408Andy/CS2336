public class Main {

    public static void main(String[] args) {

        String testStr = "aaaaaaBCEGFAASDASDA";
        String fixedStr = "";
        
        Character[] charArray = new Character[testStr.length()];
        for (int arrayIndex = 0; arrayIndex < testStr.length(); arrayIndex++) {

            charArray[arrayIndex] = testStr.charAt(arrayIndex);

        }

        for (int i = 0; i < testStr.length(); i++) {
            
            for (int j = i + 1; j < testStr.length(); j++) {
               
                // to compare one string with other strings
                if (charArray[i].compareTo(charArray[j]) > 0) {
                    // swapping
                    Character temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;
                }

            }

        }

        for (int arrayIndex = 0; arrayIndex < testStr.length(); arrayIndex++) {

            fixedStr += charArray[arrayIndex];

        }

        System.out.println(fixedStr);

    }

}