package sr.unasat.Admin.Entities;

public enum TYPE {
    CHECKING("chequings"),
    SAVINGS("savings");

    private final String type;

    TYPE(String type){ this.type=type; }
}
