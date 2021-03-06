package project3;

import project3.cFold.CFolder;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ballololz on 11/30/2015.
 */
public class Runner {
    public static void main(String[] args) {
        String result1 = new SequenceFolder().fold("hhppppphhppphppphp");
        String result2 = new ImprovedSequenceFolder().fold("hhppppphhppphppphp");
        System.out.println(result1);
        System.out.println(result2);

        //runAll();
    }

    public static void runAll() {
        FileWriter fw=null;
        SequenceFolder sf = new SequenceFolder();

        String outp = "";

        String[] inps = {
                "hhppppphhppphppphp",
                "hphphhhppphhhhpphh",
                "phpphphhhphhphhhhh",
                "hphpphhphpphphhpphph",
                "hhhpphphphpphphphpph",
                "hhpphpphpphpphpphpphpphh",
                "pphpphhpppphhpppphhpppphh",
                "ppphhpphhppppphhhhhhhpphhpppphhpphpp",
                "pphpphhpphhppppphhhhhhhhhhpppppphhpphhpphpphhhhh",
                "hhphphphphhhhphppphppphpppphppphppphphhhhphphphphh",
                "pphhhphhhhhhhhppphhhhhhhhhhphppphhhhhhhhhhhhpppphhhhhhphhphp",
                "hhhhhhhhhhhhphphpphhpphhpphpphhpphhpphpphhpphhpphphphhhhhhhhhhhh",
                "hhhhpppphhhhhhhhhhhhpppppphhhhhhhhhhhhppphhhhhhhhhhhhppphhhhhhhhhhhhppphpphhpphhpphph",
                "pppppphphhppppphhhphhhhhphhpppphhpphhphhhhhphhhhhhhhhhphhphhhhhhhppppppppppphhhhhhhpphphhhpppppphphh",
                "ppphhpphhhhpphhhphhphhphhhhpppppppphhhhhhpphhhhhhppppppppphphhphhhhhhhhhhhpphhhphhphpphphhhpppppphhh"};

        for(int i=0;i<inps.length;i++){
            outp+=(String.format("py -2 hpview.py %s %s > folds/%d.txt\n", inps[i],sf.fold(inps[i]),i));

        }

        try
        {
            fw = new FileWriter("RUN.bat");
            fw.write(outp);
            fw.close();
        }catch(IOException ioe){
            System.out.printf("ioerror");
        }
        ScoreFinder scofi = new ScoreFinder();
        System.out.println(scofi.findScore(inps[12],sf.fold(inps[12])));
    }
}
