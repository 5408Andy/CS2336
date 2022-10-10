/* 
 * HW Review #1
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/9/2022
 * Class & Section: CS - 2366.003
 */

import java.util.ArrayList;

public class GenericList <ListType extends Comparable<ListType>> {
    
    private ArrayList<ListType> arrayList;

    GenericList(ArrayList<ListType> arrayList) {

        this.arrayList = arrayList;

    } // GenericList - Overloaded Constructor

    public void setList(ListType elementReceived) {

        arrayList.add(elementReceived);

    } // setList

    public ArrayList<ListType> getList() {

        return arrayList;

    } // getList

    public void InsertionSort() {

        for (int arrayIndex = 1; arrayIndex < arrayList.size(); arrayIndex++) {
            ListType keyValue = arrayList.get(arrayIndex);
            int swapIndex = arrayIndex - 1;
  
            // finds value less than the key value 
            while (swapIndex >= 0 && arrayList.get(swapIndex).compareTo(keyValue) > 0) {
                
                arrayList.set(swapIndex + 1, arrayList.get(swapIndex));
                swapIndex = swapIndex - 1;

            }

            arrayList.set(swapIndex + 1, keyValue); // swap the values
        }

    } // InsertionSort

    public boolean BinarySearch(ListType elementReceived) {

        int lowPoint = 0;
        int highPoint = arrayList.size();

        while (0 < arrayList.size()) {
            int midPoint = lowPoint + (highPoint - lowPoint) / 2; // find the midpoint of the array list
      
            if (arrayList.get(midPoint).compareTo(elementReceived) == 0) {
              
                return true;
                
            }
            if (arrayList.get(midPoint).compareTo(elementReceived) > 0) {

              lowPoint = midPoint + 1;
      
            }
            else {

              highPoint = midPoint - 1;

            }
      
        }

        return false;

    } // BinarySearch

}
