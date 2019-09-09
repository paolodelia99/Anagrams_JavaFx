import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Permutation {
    String wordToPermute;
    ArrayList<String> anagrams = new ArrayList<>();
    int stringLenght;

    public Permutation(String wordToPermute) {
        this.wordToPermute = wordToPermute.toLowerCase();
        stringLenght = wordToPermute.length();
    }

    public ArrayList<String> returnAnagrams(){
        permute(wordToPermute,0,stringLenght-1);
        Collections.sort(anagrams);
        return anagrams;
    }

    private void permute(String str, int l, int r)
    {
        if (l == r)
            anagrams.add(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }

    private String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
