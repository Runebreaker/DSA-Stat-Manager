package FunctionalStuff;

public enum AttributeNames
{
    //Format: mu, kl, in, ch, ff, ge, ko, kk
    MU("Mut"),
    KL("Klugheit"),
    IN("Intuition"),
    CH("Charisma"),
    FF("Fingerfertigkeit"),
    GE("Geschicklichkeit"),
    KO("Konstitution"),
    KK("Körperkraft");

    private String Stat;

    AttributeNames(String Stat) {
        this.Stat = Stat;
    }

    public String getStat() {
        return Stat;
    }
}
