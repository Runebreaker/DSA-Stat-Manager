package FunctionalStuff;

public class Functionality
{
    private int[] stats = new int[6];
    private int[] painBorder = new int[4];

    public Functionality()
    {
        this(0, 0, 0, 0, 0, 0);
    }

    public Functionality(int[] list)
    {
        this(list[0], list[1], list[2], list[3], list[4], list[5]);
    }

    public Functionality(int h, int hm, int k, int km, int a, int am)
    {
        int[] temp = {h, hm, k, km, a, am};
        for(int i : stats)
        {
            stats[i] = temp[i];
        }
        for(int i : painBorder)
        {
            painBorder[i] = (int) Math.ceil(h/4.0);
        }
    }

    public int getHealth() {
        return stats[StatNames.H.getListNumber()];
    }

    public void setHealth(int health) {
        stats[StatNames.H.getListNumber()] = health;
    }

    public int getHealthMax() {
        return stats[StatNames.HM.getListNumber()];
    }

    public void setHealthMax(int healthMax) {
        stats[StatNames.HM.getListNumber()] = healthMax;
    }

    public int getKarma() {
        return stats[StatNames.K.getListNumber()];
    }

    public void setKarma(int karma) {
        stats[StatNames.K.getListNumber()] = karma;
    }

    public int getKarmaMax() {
        return stats[StatNames.KM.getListNumber()];
    }

    public void setKarmaMax(int karmaMax) {
        stats[StatNames.KM.getListNumber()] = karmaMax;
    }

    public int getAstral() {
        return stats[StatNames.A.getListNumber()];
    }

    public void setAstral(int astral) {
        stats[StatNames.A.getListNumber()] = astral;
    }

    public int getAstralMax() {
        return stats[StatNames.AM.getListNumber()];
    }

    public void setAstralMax(int astralMax) {
        stats[StatNames.AM.getListNumber()] = astralMax;
    }
}
