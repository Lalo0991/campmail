package mx.com.d4.models;

public enum RequestType {

	REQ_CONTACT("1"), REQ_CANCEL("2"), CONTACT("contact"), CANCEL("cancel"), R1("r1"), R2("r2"), R3("r3"), R4("r4");

	private String value;

	RequestType(String value) {
		this.value = value;
	}

	String getValue() {
		return this.value;
	}
	
	@Override
    public String toString() {
        return value;
    }

}
