package project2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Thomas on 11-09-2015.
 */
public class NewickWriter {
    PrintWriter writer;

    public NewickWriter(String filepath) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(filepath, "UTF-8");
    }

    public void write(String newickTree){
        writer.print(newickTree);
        writer.close();
    }
}
