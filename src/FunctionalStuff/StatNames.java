package FunctionalStuff;

public enum StatNames {
    H("HEALTH"),
    HM("HEALTH_MAX"),
    K("KARMA"),
    KM("KARMA_MAX"),
    A("ASTRAL"),
    AM("ASTRAL_MAX"),

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

    StatNames(String Stat) {
        this.Stat = Stat;
    }

    public String getStat() {
        return Stat;
    }
}
