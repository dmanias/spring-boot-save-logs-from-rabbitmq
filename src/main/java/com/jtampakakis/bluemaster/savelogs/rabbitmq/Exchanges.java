package com.jtampakakis.bluemaster.savelogs.rabbitmq;

public enum Exchanges {
	admin("administration"),
	config("configuration"),
	licensing("licensing"),
	crewpins("crewpins"),
	management("management");
	
	public final String label;

    private Exchanges(String label) {
        this.label = label;
    }
}
