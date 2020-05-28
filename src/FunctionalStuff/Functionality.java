package FunctionalStuff;

import java.util.ArrayList;
import java.util.Map;

public class Functionality {
    private int[] stats;
    private int[] painBorder = new int[4];
    private ArrayList<DiceProp> diceProps = new ArrayList<>();
    private Map<AttributeNames, Integer> attributes;

    public Functionality(int[] list, Map<AttributeNames, Integer> attributes) {
        this.stats = list;
        for (int i : painBorder) {
            painBorder[i] = (int) Math.ceil(list[1] / 4.0);
        }
        this.attributes = attributes;
        for(AttributeNames a : AttributeNames.values())
        {
            attributes.put(a, 0);
        }
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public int[] getPainBorder() {
        return painBorder;
    }

    public ArrayList<DiceProp> getDiceProps() {
        return diceProps;
    }

    public boolean changeDice(int sides, int amount) {
        int total = amount;
        for(DiceProp d : diceProps)
        {
            if(d.getSides() == sides)
            {
                total += d.getAmount();
                diceProps.remove(d);
            }
        }
        if(total > 0)diceProps.add(new DiceProp(sides, total));
        return total <= 0;
    }

    public Map<AttributeNames, Integer> getAttributes() {
        return attributes;
    }

    public void editAttributes(AttributeNames attributeNames, int newValue) {
        attributes.replace(attributeNames, newValue);
    }

    public int getHealth() {
        return stats[0];
    }

    public void setHealth(int health) {
        stats[0] = health;
    }

    public int getHealthMax() {
        return stats[1];
    }

    public void setHealthMax(int healthMax) {
        stats[1] = healthMax;
    }

    public int getKarma() {
        return stats[2];
    }

    public void setKarma(int karma) {
        stats[2] = karma;
    }

    public int getKarmaMax() {
        return stats[3];
    }

    public void setKarmaMax(int karmaMax) {
        stats[3] = karmaMax;
    }

    public int getAstral() {
        return stats[4];
    }

    public void setAstral(int astral) {
        stats[4] = astral;
    }

    public int getAstralMax() {
        return stats[5];
    }

    public void setAstralMax(int astralMax) {
        stats[5] = astralMax;
    }
}
