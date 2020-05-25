package Terminal_Version;

import FunctionalStuff.DiceProp;
import FunctionalStuff.Functionality;
import FunctionalStuff.Roll;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class terminal
{
    private Scanner sc = new Scanner(System.in);
    private Functionality f;
    private DiceProp[] dp = {
            new DiceProp(2, 0),
            new DiceProp(4,0),
            new DiceProp(6,0),
            new DiceProp(8,0),
            new DiceProp(12,0),
            new DiceProp(20,0),
            new DiceProp(100,0)};

    public static void main(String[] args) {
        terminal t = new terminal();
    }

    public int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i);
        }
        return ret;
    }

    private terminal() {
        ArrayList<Integer> al = new ArrayList<>();
        //Just for Testing purposes
        for(int i = 0; i < 6; i++)
        {
            System.out.print("Next Value: ");
            al.add(sc.nextInt());
            sc.nextLine();
        }

        f = new Functionality(convertIntegers(al));
        dp[0].setAmount(0);
        dp[5].setAmount(100);

        System.out.println(output(roll()));
    }

    private ArrayList<Roll> roll()
    {
        ArrayList<Roll> temp = new ArrayList<>();
        for(DiceProp d : dp)
        {
            temp.addAll(d.roll());
        }
        return temp;
    }

    private String output(ArrayList<Roll> in)
    {
        StringBuilder sb = new StringBuilder();
        for (Roll i : in) {
            if(i.getValue() == 1)
            sb.append(i.getDie()).append(": ").append(i.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
