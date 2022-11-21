// ArrayList
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        ArrayList<Integer> testList = new ArrayList<Integer>(10);

        for (int arrayIndex = 0; arrayIndex < 10; arrayIndex++) {

            testList.add(arrayIndex + 2);

        } 
        
        for (int elementAtIndex : testList) {

            System.out.println(elementAtIndex);

        }
        

    }

}
