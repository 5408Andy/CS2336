public class Main {

    private static int countComparison = 0;

    private static int countSwap = 0;

    public static void main(String[] args) {

        final int maxSize = 5000;

        int[] testArr1 = new int[maxSize];
        int[] testArr2 = new int[maxSize];
        int[] testArr3 = new int[maxSize];
        int[] testArr4 = new int[maxSize];

        LinkList testList1 = new LinkList();

        for (int arrayIndex = 0; arrayIndex < maxSize; arrayIndex++) {

            int randNumGenerated = randNum();

            testArr1[arrayIndex] = randNumGenerated;
            testArr2[arrayIndex] = randNumGenerated;
            testArr3[arrayIndex] = randNumGenerated;
            testArr4[arrayIndex] = randNumGenerated;

            testList1.push(randNumGenerated);

        }

        

        

        sortSelection(testArr1);
        System.out.println("Selection | CC: " + countComparison + " SC: " + countSwap);

        countComparison = 0;
        countSwap = 0;

        sortBubble(testArr2);
        System.out.println("Bubble | CC: " + countComparison + " SC: " + countSwap);

        countComparison = 0;
        countSwap = 0;

        sortInsertion(testArr3);
        System.out.println("Insertion | CC: " + countComparison + " SC: " + countSwap);

        countComparison = 0;
        countSwap = 0;

        quickSort(testArr4,0,testArr4.length - 1);
        System.out.println("Quick | CC: " + countComparison + " SC: " + countSwap);

        countComparison = 0;
        countSwap = 0;

        // - - - - - //

        testList1.head = testList1.mergeSort(testList1.head);
        System.out.println("Merge | CC: " + testList1.comparisons);
        

    }

    private static int randNum() {

        return (int)(Math.random()*(5000-0+1)+0);  

    } // randNum

    private static void printValues(int[] arrayReceived) {

        int numCount = 0;

        for (int arrayIndex = 0; arrayIndex < arrayReceived.length; arrayIndex++) {

            numCount++;

            System.out.print(arrayReceived[arrayIndex] + " ");

            if (numCount == 50) {

                System.out.println();
                
                numCount = 0;

            }

        }

    }

    private static void sortSelection(int[] arrayReceived) {

        for (int i = 0; i < arrayReceived.length-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < arrayReceived.length; j++) {
                countComparison++;
                if (arrayReceived[j] < arrayReceived[min_idx])
                    min_idx = j;
            }
 
            // Swap the found minimum element with the first
            // element
            countSwap++;
            int temp = arrayReceived[min_idx];
            arrayReceived[min_idx] = arrayReceived[i];
            arrayReceived[i] = temp;
        }

    }

    private static void sortBubble(int[] arrayReceived) {

        for (int i = 0; i < arrayReceived.length - 1; i++)
            for (int j = 0; j < arrayReceived.length - i - 1; j++) {
                countComparison++;
                if (arrayReceived[j] > arrayReceived[j + 1]) {
                    countSwap++;
                    // swap arr[j+1] and arr[j]
                    int temp = arrayReceived[j];
                    arrayReceived[j] = arrayReceived[j + 1];
                    arrayReceived[j + 1] = temp;
                }

            }

    }

    private static void sortInsertion(int[] arrayReceived) {

        for (int i = 1; i < arrayReceived.length; ++i) {
            int key = arrayReceived[i];
            int j = i - 1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0) {
                countComparison++;
                if(arrayReceived[j] > key) {
                    countSwap++;
                    arrayReceived[j + 1] = arrayReceived[j];
                    j = j - 1;
                }
                else {
                    break;
                }

            }
            arrayReceived[j + 1] = key;
        }

    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - // 

    // A utility function to swap two elements
    private static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
  
    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    private static int partition(int[] arr, int low, int high)
    {
  
        // pivot
        int pivot = arr[high];
  
        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);
  
        for (int j = low; j <= high - 1; j++) {
            countComparison++;
            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {
                countSwap++;
                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        countSwap++;
        swap(arr, i + 1, high);
        return (i + 1);
    }
  
    /* The main function that implements QuickSort
              arr[] --> Array to be sorted,
              low --> Starting index,
              high --> Ending index
     */
    private static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {
  
            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);
  
            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

}