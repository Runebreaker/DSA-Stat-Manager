package FunctionalStuff;

public class Roll
{
    private int die, value;

    public Roll(int die, int value) {
        this.die = die;
        this.value = value;
    }

    public int getDie() {
        return die;
    }

    public int getValue() {
        return value;
    }
}
