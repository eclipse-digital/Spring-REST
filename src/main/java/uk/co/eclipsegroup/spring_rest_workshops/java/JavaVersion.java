package uk.co.eclipsegroup.spring_rest_workshops.java;

import java.util.Objects;

public class JavaVersion {
    private final String name;
    private final Double version;

    public JavaVersion(String name, Double version) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaVersion that = (JavaVersion) o;
        return Objects.equals(name, that.name) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version);
    }

    @Override
    public String toString() {
        return "JavaVersion{" +
                "name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
