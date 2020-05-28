package FunctionalStuff;

import java.util.ArrayList;

public class Functionality {
    private int[] stats = new int[6];
    private int[] painBorder = new int[4];
    private ArrayList<DiceProp> diceProps = new ArrayList<>();

    public Functionality() {
        this(0, 0, 0, 0, 0, 0);
    }

    public Functionality(int[] list) {
        this(list[0], list[1], list[2], list[3], list[4], list[5]);
    }

    public Functionality(int h, int hm, int k, int km, int a, int am) {
        int[] temp = {h, hm, k, km, a, am};
        for (int i : stats) {
            stats[i] = temp[i];
        }
        for (int i : painBorder) {
            painBorder[i] = (int) Math.ceil(h / 4.0);
        }
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
