// Jonathan Plant and Andy Nguyen    
// jtp210002 and adn200004
// 9/28/2022

public class File extends Document {
    private String pathway, filename;
    
    // constructor
    public File(String p, String f, String b) {
        super(b);
        pathway = p;
        filename = f;
    }
    
    // getters
    public String getPathway() { return(pathway); }
    public String getFilename() { return(filename); }
    
    // setters
    public void setPathway(String p) { pathway = p; }
    public void setFilename(String f) { filename = f; }
    
    // overridden toString
    @Override
    public String toString() {
        return(pathway + filename + "\n" + body);
    }
    
}