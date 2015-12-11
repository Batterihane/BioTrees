package project3;

/**
 * Created by ballololz on 11-Dec-15.
 */
public abstract class Abs2rel {

    public static String convert(String s){

        String res = "f";
        for (int i=1; i<s.length(); i++){
            char current = s.charAt(i);
            char last = s.charAt(i-1);

            if (current==last){
                res += "f";
                continue;
            }
            if (last=='n'&&current=='e' || last=='s'&&current=='w' ||last=='e'&&current=='s' ||last=='w'&&current=='n' ){
                res += "r";
                continue;
            }

            if (last=='n'&&current=='w' || last=='s'&&current=='e' ||last=='e'&&current=='n' ||last=='w'&&current=='s' ){
                res += "l";
                continue;
            }
            System.out.println("Something is wrong in Abs2Rel!");

        }
        return res;
    }
}
