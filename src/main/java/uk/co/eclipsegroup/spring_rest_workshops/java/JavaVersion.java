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
        JavaVersion javaVersion = (JavaVersion) o;
        return Objects.equals(name, javaVersion.name) && Objects.equals(version, javaVersion.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version);
    }
}
