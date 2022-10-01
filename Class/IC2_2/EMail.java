// Jonathan Plant and Andy Nguyen    
// jtp210002 and adn200004
// 9/28/2022

public class EMail extends Document {
    private String sender, recipient, title;
    
    // constructor
    public EMail(String s, String r, String t, String b) {
        super(b);
        sender = s;
        recipient = r;
        title = t;
    }
    
    // getters
    public String getSender()   { return(sender);    }
    public String getRecipient(){ return(recipient);    }
    public String getTitle()    { return(title);    }
    
    // setters
    public void setSender(String s) { sender = s; }
    public void setRecipient(String r) { recipient = r; }
    public void setTitle(String t) { title = t; }
    
    // overridden toString
    @Override
    public String toString() {
        return ("From: " + sender + "\nTo: " + recipient + "\nSubject: " + title + "\n" + body);
    }
    
}