/* 
 * Project 3: Antideriviative Calculator
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 10/27/2022
 * Class & Section: CS - 2366.003
 */

public class Main {
    
    public static void main(String[] args) {

        BinTree<Term> testTree = new BinTree<Term>();
        
        int[] bounds = new int[2];
        bounds[0] = 0;
        bounds[1] = 2;
        
        Term term1 = new Term(5, 2, bounds);
        
        testTree.insertData(term1);

        testTree.insertData(new Term(10, 3, bounds));

        testTree.traverseInOrder();

        //System.out.println(testTree.searchData(term2));


    }

}   
