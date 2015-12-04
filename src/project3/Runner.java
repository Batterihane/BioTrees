package project3;

/**
 * Created by ballololz on 11/30/2015.
 */
public class Runner {
    public static void main(String[] args) {
        SequenceFolder sequenceFolder = new SequenceFolder();
        ScoreFinder scoreFinder = new ScoreFinder();

        String[] foldsAndScores = new String[15];
        String[] input = new String[15];
        input[0]   = "hhppppphhppphppphp";
        input[1]   = "hphphhhppphhhhpphh";
        input[2]   = "phpphphhhphhphhhhh";
        input[3]   = "hphpphhphpphphhpphph";
        input[4]   = "hhhpphphphpphphphpph";
        input[5]   = "hhpphpphpphpphpphpphpphh";
        input[6]   = "pphpphhpppphhpppphhpppphh";
        input[7]   = "ppphhpphhppppphhhhhhhpphhpppphhpphpp";
        input[8]   = "pphpphhpphhppppphhhhhhhhhhpppppphhpphhpphpphhhhh";
        input[9]   = "hhphphphphhhhphppphppphpppphppphppphphhhhphphphphh";
        input[10]  = "pphhhphhhhhhhhppphhhhhhhhhhphppphhhhhhhhhhhhpppphhhhhhphhphp";
        input[11]  = "hhhhhhhhhhhhphphpphhpphhpphpphhpphhpphpphhpphhpphphphhhhhhhhhhhh";
        input[12]  = "hhhhpppphhhhhhhhhhhhpppppphhhhhhhhhhhhppphhhhhhhhhhhhppphhhhhhhhhhhhppphpphhpphhpphph";
        input[13]  = "pppppphphhppppphhhphhhhhphhpppphhpphhphhhhhphhhhhhhhhhphhphhhhhhhppppppppppphhhhhhhpphphhhpppppphphh";
        input[14]  = "ppphhpphhhhpphhhphhphhphhhhpppppppphhhhhhpphhhhhhppppppppphphhphhhhhhhhhhhpphhhphhphpphphhhpppppphhh";


        for (int i = 0; i < input.length; i++) {
            String fold = sequenceFolder.fold(input[i]);
            int score = scoreFinder.findScore(input[i], fold);
            foldsAndScores[i] = fold + "-" + score;
        }

        for (int i = 0; i < input.length; i++) {
            System.out.println( (i+1) + ": "+ input[i] + " " + foldsAndScores[i]);
        }



    }
}
