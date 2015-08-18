package tr.com.agem.arya.gateway;

public enum RequestTypes {

    VIEW_ONLY("V"),
    DATA_ONLY("D"),
    ALL("A");

    private final String type;
    RequestTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
