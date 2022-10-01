// Jonathan Plant and Andy Nguyen    
// jtp210002 and adn200004
// 9/28/2022

public class Document {
    protected String body;
    
    // constructor
    public Document(String d) {
        body = d;
    }
    
    // getter
    public String getBody() {
        return(body);
    }
    
    // setter
    public void setBody(String d) {
        body = d;
    }
    
    // overridden toString
    @Override
    public String toString() {
        return(body);
    }
}