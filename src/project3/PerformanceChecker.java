package project3;

import project3.cFold.CFolder;

public class PerformanceChecker {
    CFolder folder;

    public PerformanceChecker() {
        folder = new CFolder();
    }

    public static void main(String[] args) {
        PerformanceChecker checker = new PerformanceChecker();
        String[] hpStrings = checker.getHpStrings();
        for (String hpString : hpStrings){
            System.out.println(checker.getPerformance(hpString));
        }

    }

    public long getPerformance(String hpString){
        long startTime;
        long sum = 0;
        for (int i = 0; i < 1000; i++) {
            startTime = System.nanoTime();
            folder.fold(hpString);
            sum += System.nanoTime() - startTime;
        }
        return sum/1000;
    }

    public String[] getHpStrings() {
        String[] res = {
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
        return res;
    }

}
