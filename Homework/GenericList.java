/* 
 * HW Review #1
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/9/2022
 * Class & Section: CS - 2366.003
 */

import java.util.ArrayList;

public class GenericList <ListType> {
    
    private ArrayList<ListType> arrayList;

    GenericList(ArrayList<ListType> arrayListReceived) {

        arrayList = arrayListReceived;

    } // GenericList - Overloaded Constructor

    public void setList(ListType elementReceived) {

        arrayList.add(elementReceived);

    } // setList

    public ArrayList<ListType> getList() {

        return arrayList;

    } // getList

    public void InsertionSort() {

        

    } // InsertionSort

    public boolean BinarySearch() {

        return false;

    } // BinarySearch

}
