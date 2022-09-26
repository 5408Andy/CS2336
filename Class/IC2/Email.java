public class Email extends Document {
    
    protected String sender;
    protected String recipient;
    protected String title;

    Email() {

        super();

        sender = "";
        recipient = "";
        title = "";

    }

    Email(String senderReceived, String recipientReceived, String titleRecieved, String textReceived) {

        super(textReceived);

        sender = "";
        recipient = "";
        title = "";

    }

    public void setSender(String senderReceived) {

        sender = senderReceived;

    }

    public String getSender() {

        return sender;

    }

    public void setRecipient(String recipientReceived) {

        recipient = recipientReceived;

    }

    public String getRecipient() {

        return recipient;

    }

    public void setTitle(String titleRecieved) {

        title = titleRecieved;

    }

    public String getTitle() {

        return title;

    }

    public String toString() {

        return "Sender: " + sender + " Recipient: " + recipient + " Title: " + title + " Textual Content: " + super.textualContent;

    }

}
