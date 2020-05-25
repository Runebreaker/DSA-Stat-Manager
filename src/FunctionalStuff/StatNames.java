package FunctionalStuff;

public enum StatNames {
    H("HEALTH", 0),
    HM("HEALTH_MAX", 1),
    K("KARMA", 2),
    KM("KARMA_MAX", 3),
    A("ASTRAL", 4),
    AM("ASTRAL_MAX", 5);

    private String Stat;
    private int listNumber;

    StatNames(String Stat, int listNumber) {
        this.Stat = Stat;
        this.listNumber = listNumber;
    }

    public String getStat() {
        return Stat;
    }

    public int getListNumber() {
        return listNumber;
    }
}
