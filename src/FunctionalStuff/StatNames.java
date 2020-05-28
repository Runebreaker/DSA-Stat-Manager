package FunctionalStuff;

public enum StatNames {
    H("HEALTH"),
    HM("HEALTH_MAX"),
    K("KARMA"),
    KM("KARMA_MAX"),
    A("ASTRAL"),
    AM("ASTRAL_MAX");

    private String Stat;

    StatNames(String Stat) {
        this.Stat = Stat;
    }

    public String getStat() {
        return Stat;
    }
}
