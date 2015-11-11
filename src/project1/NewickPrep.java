package project1;


import newick.NewickParser;

import newick.NewickParser.*;
import newick.ParseException;

import java.io.*;


/**
 * Created by ballololz on 11/11/2015.
 */
public class NewickPrep {

    FileInputStream fs;
    FileWriter fw;

    public NewickPrep(){

    }

    public InputStream prep(String inputFile)throws IOException{
        try{
        fs = new FileInputStream(inputFile);
        }catch(FileNotFoundException fnf){
            System.out.println("Could not find file " + inputFile);
        }

        boolean inquotes=false;
        String output = "";
        int cur1 = fs.read();
        int cur2 = fs.read();
        while(cur1 != -1 && cur2 != -1) {
            char c1 = (char) cur1;
            char c2 = (char) cur2;

          while(c2=='\r' ||c2=='\n'){
               cur2 =  fs.read();
              c2 = (char)cur2;
          }


            output+=c1;
            if(     inquotes && c2 == ':'
                    || !inquotes && c1 == '(' && c2 !='('
                    || !inquotes && c1==','&&c2!='('
                    )
            {
                output += '"';
                inquotes = !inquotes;}

            cur1 = cur2;
            cur2 = fs.read();
        }
        if (cur1 != -1){
            output+=(char)cur1;}

        return new ByteArrayInputStream( output.getBytes() );
    }

}
