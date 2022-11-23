// ArrayList
import java.util.ArrayList;
import java.util.Comparator;

public class Test {

    public static void main(String[] args) {

        ArrayList<Integer> test1 = new ArrayList<Integer>();

        test1.add(0);
        
        test1.add(8);
        
        test1.add(-5);
        
        test1.add(21);
        
        test1.add(100);
        
        test1.sort(Comparator.naturalOrder());

        System.out.println(test1);
    }

}
