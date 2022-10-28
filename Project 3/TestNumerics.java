public class TestNumerics {

    public static void main(String[] args) {
        
        String str = "0";
        String str1 = "-30000";
        System.out.println(isNumericInt(str));
        System.out.println(isNumericInt(str1));

    }

    private static boolean isNumericInt(String receivedStr){
        
        try {  
            
            Integer.parseInt(receivedStr);  
            
            return true;

        } 
        catch(NumberFormatException e){  
            
            return false;  
          
        }
    }

}