/*
 * Description: This class represents a test file with its filename, spam probability, and actual class.
 * It provides methods to get and set these attributes, as well as a method to round the spam probability to five decimal places.
 */

import java.text.DecimalFormat;

public class TestFile {
    private String filename;
    private double spamProbability;
    private String actualClass;
    
    public TestFile(String filename, double spamProbability, String actualClass) {
        this.filename = filename;
        this.spamProbability = spamProbability;
        this.actualClass = actualClass;
    }
    
    public String getFilename() { return this.filename; }
    public double getSpamProbability() { return this.spamProbability; }
    public String getSpamProbRounded() {
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(this.spamProbability);
    }
    public String getActualClass() { return this.actualClass; }
    
    public void setFilename(String value) { this.filename = value; }
    public void setSpamProbability(double value) { this.spamProbability = value; }
    public void setActualClass(String value) { this.actualClass = value; }
}
