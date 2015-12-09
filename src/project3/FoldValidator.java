package project3;

public class FoldValidator
{
    char[][] foldMatrix;
    String fold;
    String hpString;

    public FoldValidator() {
    }

    private void init(String hpString, String fold)
    {
        int size = 2*fold.length() + 1;
        this.foldMatrix = new char[size][size];
        this.fold = fold;
        this.hpString = hpString;
    }

    public boolean validate(String hpString, String fold)
    {
        init(hpString, fold);

        if (hpString.length() != (fold.length()+1)) return false;

        int indexX = fold.length();
        int indexY = fold.length();
        foldMatrix[indexX][indexY] = hpString.charAt(0);

        for (int i = 0; i < fold.length(); i++)
        {
            switch (fold.charAt(i)) {
                case 'e' :
                    indexX += 1;
                    break;
                case 'w' :
                    indexX -= 1;
                    break;
                case 'n' :
                    indexY += 1;
                    break;
                case 's' :
                    indexY -= 1;
                    break;
            }

            if (foldMatrix[indexX][indexY] == '\u0000') foldMatrix[indexX][indexY] = hpString.charAt(i);
            else return false;
        }

        return true;
    }


    public static void main(String[] args) {
        char[] chars = new char[10];
        System.out.println("flfrrflffrrflrrlf".length());
        System.out.println("ennesseeeswwswnww".length());
        System.out.println("hhppppphhppphppphp".length());

        System.out.println((chars[5] == '\u0000'));

        FoldValidator foldValidator = new FoldValidator();
        System.out.println(foldValidator.validate("hhppppphhppphppphp", "eeeswnnnesseeesww"));

    }
}
