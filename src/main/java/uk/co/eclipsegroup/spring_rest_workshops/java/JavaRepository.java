package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JavaRepository {
    private final List<Java> repository = new ArrayList<>();

    public List<Java> fetchAll() {
        return repository;
    }

    public void save(Java java) {
        repository.add(java);
    }

    public void remove(String javaVersion) {
        repository.removeIf(j -> j.getName().equals(javaVersion));
    }
}
