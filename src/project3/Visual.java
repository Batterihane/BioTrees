package project3;

import project3.cFold.CFolder;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ballololz on 04-Dec-15.
 */
public class Visual {

    public static void main(String[] args) {
        String writeLocation = "project3/visual/RUN.bat";
        FileWriter fw=null;
        CFolder cfold = new CFolder();

        String vis_outp = "";
        String res_outp = "";

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
        ////code for running the visualizer
        for(int i=0;i<inps.length;i++){

            String s = cfold.fold(inps[i]);
            int score = scofi.findScore(inps[i],s);
            vis_outp+=(String.format("py -2 hpview.py %s %s > newfolds/fold%d_score%d.txt\n", inps[i],Abs2rel.convert(s),i+1,score));

        }

        /////Code for writing results.txt
        for(int i=0;i<inps.length;i++){
            String s = cfold.fold(inps[i]);
            int score = scofi.findScore(inps[i],s);
            res_outp+=(String.format("%d: %s %s -%d\r\n", i+1,inps[i],Abs2rel.convert(s),score));

        }


        try
        {
            fw = new FileWriter("project3/visual/vis.bat");
            fw.write(vis_outp);
            fw.close();
            fw = new FileWriter("project3/visual/results.txt");
            fw.write(res_outp);
            fw.close();
        }catch(IOException ioe){
            System.out.printf("ioerror");
        }



    }
}
