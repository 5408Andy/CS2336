/* 
 * Project 4: Super Mario Sluggers with Hashmaps
 * Name: Andy Nguyen 
 * Net ID: adn200004
 * Date: 11/09/2022
 * Class & Section: CS - 2366.003
 */

 // ArrayList
import java.util.ArrayList;

public class GenericHashMap<K, V> {
    
    private class Entry<Key, Value> {

        private Key keyItem;
        private Value valueItem;
        private Entry <Key,Value> nextItem;

        public Entry(Key keyItemReceived, Value valueItemReceived) {

            keyItem = keyItemReceived;
            valueItem = valueItemReceived;

        } // Constructor - Entry

        // Getter Methods

        public Key getKey() { return keyItem; }

        public Value getValue() { return valueItem; }

        // Setter Methods

        public void setValue(Value valueItemReceived) { valueItem = valueItemReceived; }

    } // Private Class - Entry

    private int hashTableSize = 11;
    
    private ArrayList<Entry<K, V>> hashTable;

    public GenericHashMap() {

        hashTable = new ArrayList<Entry<K, V>>(hashTableSize);

    } // Constructor - GenericHashMap
    

    public void put(K keyItemReceived, V valueItemRecieved) {

        //int hashCode = keyItemReceived.hashCode();
    
    }

}
