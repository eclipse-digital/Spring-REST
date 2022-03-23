package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JavaVersionRepository {
    private final List<JavaVersion> repository = new ArrayList<>();

    public List<JavaVersion> fetchAll() {
        return repository;
    }

    public void save(JavaVersion javaVersion) {
        repository.add(javaVersion);
    }

    public void remove(String javaVersion) {
        repository.removeIf(j -> j.getName().equals(javaVersion));
    }
}
