package FunctionalStuff;

public enum AttributeNames
{
    //Format: mu, kl, in, ch, ff, ge, ko, kk
    MU("Mut", 0),
    KL("Klugheit", 1),
    IN("Intuition", 2),
    CH("Charisma", 3),
    FF("Fingerfertigkeit", 4),
    GE("Geschicklichkeit", 5),
    KO("Konstitution", 6),
    KK("Körperkraft", 7);

    private String Stat;
    private int value;

    AttributeNames(String Stat, int value) {
        this.Stat = Stat;
        this.value = value;
    }

    public String getStat() {
        return Stat;
    }

    public int getValue() {
        return value;
    }
}
