package FunctionalStuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class DiceProp implements Serializable
{

    private int sides, amount;
    Random r;

    public DiceProp(int sides, int amount){
        this.sides = sides;
        this.amount = amount;
        r = new Random(System.currentTimeMillis());
    }

    public ArrayList<Roll> roll()
    {
        ArrayList<Roll> rolls = new ArrayList<>();
        for(int i = 0; i < amount; i++)
        {
            rolls.add(new Roll(sides, r.nextInt(sides) + 1));
        }
        return rolls;
    }

    public int getSides() {
        return sides;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
