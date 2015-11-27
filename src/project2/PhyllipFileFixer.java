package project2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Thomas on 27-11-2015.
 */
public class PhyllipFileFixer {
    static final String delimiter = "   ";
    static final String MATRICES_PATH = "project2//distance_matrices//";

    public static void main(String[] args) throws IOException {
        Files.walk(Paths.get(MATRICES_PATH)).forEach(filepath -> {
            if (!filepath.getFileName().toString().equals("distance_matrices")) {
                doIt(filepath.toString());
            }
        });

    }

    public static void  doIt(String filepath) {
        List<String[]> lines = new ArrayList<>();
        String line = null;
        try {
            File file = new File(filepath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String[] firstLine = new String[1];
            firstLine[0] = bufferedReader.readLine(); // skip first line
            lines.add(firstLine);
            for (int i = 0 ; (line = bufferedReader.readLine()) != null ; i++) {
                String[] parts = line.split(delimiter);
                parts[0] = parts[0] + "_" + i;
                lines.add(parts);
            }
            fileReader.close();
            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String[] lineToWrite : lines){
                for (String s : lineToWrite){
                    bufferedWriter.write(s + delimiter);
                }
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
