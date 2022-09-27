public class Main {

    public static void main (String[] args) {

        System.out.println("\nKeyword Search!\n");

        String keywordSearch = "test";

        String keywordSearch2 = "Andy";

        String keywordSearch3 = "Tom";

        Document doc1 = new Document("This is a test!");

        Document doc2 = new Email("Andy", "Tom", "Chores List", "This is a test!");

        Document doc3 = new File("C:\\Users\\Andy\\OneDrive - The University of Texas at Dallas\\Sophomore\\UTD Images", "textFile.txt", "This is a test!");

        System.out.println("Does Document 1 contain \"" + keywordSearch +"\"? " + containsKeyword(doc1, keywordSearch));

        System.out.println("Does Document 2 contain \"" + keywordSearch2 + "\"? " + containsKeyword(doc2, keywordSearch2));

        System.out.println("Does Document 3 contain \"" + keywordSearch3 + "\"? " + containsKeyword(doc3, keywordSearch3));

        System.out.println();

        System.out.println("Document 1's content | " + doc1.toString() + "\n");

        System.out.println("Document 2's content | " + doc2.toString() + "\n");

        System.out.println("Document 3's content | " + doc3.toString() + "\n");

    }

    public static Boolean containsKeyword(final Document docWork, String keywordSearch) {

        Boolean keywordFound = false;

        String analyzeDocString = docWork.getTextualContent();

        if (analyzeDocString.indexOf(keywordSearch) != -1) {

            keywordFound = true;

        }

        if (docWork instanceof Email) {

            System.out.println("Document is an Email!");

            String analyzeEmailString = ((Email)docWork).getSender();

            if (analyzeEmailString.indexOf(keywordSearch) != -1) {

                keywordFound = true;
    
            }

            analyzeEmailString = ((Email)docWork).getRecipient();

            if (analyzeEmailString.indexOf(keywordSearch) != -1) {

                keywordFound = true;
    
            }

            analyzeEmailString = ((Email)docWork).getTitle();

            if (analyzeEmailString.indexOf(keywordSearch) != -1) {

                keywordFound = true;
    
            }

        }

        if (docWork instanceof File) {

            System.out.println("Document is a File!");

            String analyzeFileString = ((File)docWork).getPathname();

            if (analyzeFileString.indexOf(keywordSearch) != -1) {

                keywordFound = true;

            }

            analyzeFileString = ((File)docWork).getFilename();

            if (analyzeFileString.indexOf(keywordSearch) != -1) {

                keywordFound = true;

            }

        }

        return keywordFound;

    }

}
