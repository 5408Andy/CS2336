public class GenericMethod {
    
    public static void main(String[] args) {

        Integer item1 = 1;
        Integer item2 = 2;
        Integer item3 = 3;

        Double Ditem1 = 1.5;
        Double Ditem2 = 28.1;
        Double Ditem3 = 3.4;

        System.out.println(getMax(item1, item2, item3));

        System.out.println(getMax(Ditem1, Ditem2, Ditem3));

    }

    public static <G extends Comparable<G>> G getMax(G item1, G item2, G item3) {

        G maxVal = item1;

        if (item2.compareTo(maxVal) > 0) {
            maxVal = item2;
         }
         if (item3.compareTo(maxVal) > 0) {
            maxVal = item3;
         }

        return maxVal;

    }

}
