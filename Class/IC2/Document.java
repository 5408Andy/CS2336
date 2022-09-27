public class Document {

    protected String textualContent;

    Document() { textualContent = ""; } // constructor

    Document(String textReceived) { textualContent = textReceived; } // constructor

    public void setTextualContent(String textReceived) { // mutater

        textualContent = textReceived;

    }

    public String getTextualContent() { // accessor

        return textualContent;

    }

    @Override
    public String toString() {

        return "Textual Content: " + textualContent;

    }

}