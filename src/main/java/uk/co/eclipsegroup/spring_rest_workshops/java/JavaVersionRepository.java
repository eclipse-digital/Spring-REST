package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JavaVersionRepository {
    private final List<JavaVersion> javaVersions = new ArrayList<>();

    public List<JavaVersion> fetchAll() {
        return javaVersions;
    }

    public void save(JavaVersion javaVersion) {
        javaVersions.add(javaVersion);
    }
}
