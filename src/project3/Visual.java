package project3;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ballololz on 04-Dec-15.
 */
public class Visual {

    public static void main(String[] args) {
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

        ScoreFinder scofi = new ScoreFinder();
        for(int i=0;i<inps.length;i++){
            String s = sf.fold(inps[i]);
            outp+=(String.format("py -2 hpview.py %s %s > folds/fold%d_score%d.txt\n", inps[i],s,i,scofi.findScore(inps[i],s)));

        }

        try
        {
            fw = new FileWriter("project3/visual/RUN.bat");
            fw.write(outp);
            fw.close();
        }catch(IOException ioe){
            System.out.printf("ioerror");
        }


        for (int i = 0;i<inps.length;i++)//print scores til sammenligning
            System.out.println("fold "+i+" score: "+ scofi.findScore(inps[i],sf.fold(inps[i])));




    }
}
