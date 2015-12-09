package project3;

/**
 * Created by ballololz on 12/2/2015.
 */
public class ScoreFinder {

    public int findScore(String hp, String fold){
        int score = 0;
        int iud=0, ilr = 0;

        for(int i=0; i<fold.length();i++){


            if (hp.charAt(i)=='h'){ //if char at i is an h, try to score it
                int jud=iud, jlr=ilr;

                for(int j = i ;j<fold.length();j++){


                    if(((iud-jud==0||ilr-jlr==0)&&(Math.abs(iud-jud)==1||Math.abs(ilr-jlr)==1)) //if one of ud and lr is 0 and one of them is 1 in absolute terms
                            &&hp.charAt(j)=='h' //and the char at j is an h
                            &&Math.abs(i-j)>1){ //and j is not i or next to i
                        score++;

                    }

                    switch(fold.charAt(j)){ //update j-pos
                        case 'n':
                            jud++;
                            break;
                        case 's':
                            jud--;
                            break;
                        case 'e':
                            jlr++;
                            break;
                        case 'w':
                            jlr--;
                            break;
                    }

                }


                //den her if scorer for j+1 som ikke har en tilsvarende værdi i fold.
                // Man kan fjerne den og istedet fra starten appende en dummyværdi til fold. (er testet)
                if(((iud-jud==0||ilr-jlr==0)&&(Math.abs(iud-jud)==1||Math.abs(ilr-jlr)==1))
                        &&hp.charAt(fold.length())=='h'
                        &&Math.abs(i-fold.length())>1){
                    score++;

                }




            }

            switch(fold.charAt(i)){ //update i-pos
                case 'n':
                    iud++;
                    break;
                case 's':
                    iud--;
                    break;
                case 'e':
                    ilr++;
                    break;
                case 'w':
                    ilr--;
                    break;
            }


        }

        return score;
    }
}
