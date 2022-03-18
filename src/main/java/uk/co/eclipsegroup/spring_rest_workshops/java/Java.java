package uk.co.eclipsegroup.spring_rest_workshops.java;

public class Java {
    private final String name;
    private final Double version;

    public Java(String name, Double version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public Double getVersion() {
        return version;
    }
}
