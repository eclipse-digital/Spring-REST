package uk.co.eclipsegroup.spring_rest_workshops.java;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Java{" +
                "name='" + name + '\'' +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Java java = (Java) o;
        return Objects.equals(name, java.name) && Objects.equals(version, java.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version);
    }
}
