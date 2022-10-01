// Jonathan Plant and Andy Nguyen    
// jtp210002 and adn200004
// 9/28/2022

public class Main
{
	public static void main(String[] args) {
		
		Document doc = new Document("Hello, how are you?");
		EMail docEmail = new EMail("Andy", "Jonathan", "IC2 Assighment", "Hello, whats up!");
		File docFile = new File("/Downloads/CS2/IC2/", "IC2_project.java", "Yay");
		
		//keywords 
		String keyword = "Andy";
		String keyword2 = "Hello";
		String keyword3 = "Yay";
		
		
		Boolean keywordC1 = containsKeyword(doc, keyword); // TEST CASE 1: Document search for keyword #1
		
		if (keywordC1 == true) {
		    
		    System.out.println("Document contains " + keyword);
		    
		}
		else {
		    
		    System.out.println("Document does not contain " + keyword);
		    
		}
		
		Boolean keywordC2 = containsKeyword(docEmail, keyword2); // TEST CASE 2: Email search for keyword #2
		
		if (keywordC2 == true) {
		    
		    System.out.println("Email contains " + keyword2);
		    
		}
		else {
		    
		    System.out.println("Email does not contain " + keyword2);
		    
		}
		
		Boolean keywordC3 = containsKeyword(docFile, keyword3); // TEST CASE 3: File search for keyword #3
		
		if (keywordC3 == true) {
		    
		    System.out.println("File contains " + keyword3);
		    
		}
		else {
		    
		    System.out.println("File does not contain " + keyword3);
		    
		}
		
		//  Contents/toString of each document
		System.out.println("\nDocument contents: \n" + doc);
		
		System.out.println("\nEmail contents: \n" + docEmail);
		
		System.out.println("\nFile contents: \n" + docFile);
		
		
	}
	
	public static Boolean containsKeyword(final Document doc, String keyword) {
	    
	    String textCont = doc.getBody();
	    
	    if (doc instanceof EMail) { // if doc is instance of email, add member variables of email to string
	        
	        EMail temp = (EMail)doc;
	        
	        textCont += temp.getSender();
	        textCont += temp.getRecipient();
	        textCont += temp.getTitle();
	    }
	    
	    if (doc instanceof File) { // if doc is instance of file, add member variables of email to string
	        
	        File temp = (File)doc;
	        
	        textCont += temp.getPathway();
	        textCont += temp.getFilename();
	        
	    }
	    
	    if (textCont.indexOf(keyword) != -1) { // if keyword is found, return true
	        
	        return true;
	        
	    }
	    
	    return false; // indexOf returned -1 so keyword was not found
	}
}
