public class File extends Document{
    
    protected String pathname;
    protected String filename;

    File() {

        super();

        pathname = "";
        filename = "";

    }

    File(String pathnameReceived, String filenameReceived, String textReceived) {

        super(textReceived);

        pathname = pathnameReceived;
        filename = filenameReceived;

    }

    public void setPathname(String pathnameReceived) {

        pathname = pathnameReceived;

    }

    public String getPathname() {

        return pathname;

    }

    public void setFilename(String filenameReceived) {

        filename = filenameReceived;

    }

    public String getFilename() {

        return filename;

    }

    @Override
    public String toString() {

        return "Pathname: " + pathname + " | Filename: " + filename + " | Textual Content: " + super.textualContent;

    }

}
