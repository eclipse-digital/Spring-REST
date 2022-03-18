package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JavaRepository {
    private final List<Java> repository = new ArrayList<>();

    public JavaRepository() {
        repository.add(new Java("JDK 1.0", 1.0));
        repository.add(new Java("JDK 1.1", 1.1));
        repository.add(new Java("Java SE 7", 7.0));
        repository.add(new Java("Java SE 8 (LTS)", 8.0));
        repository.add(new Java("Java SE 11 (LTS)", 11.0));
        repository.add(new Java("Java SE 17 (LTS)", 17.0));
    }

    public List<Java> fetchAll() {
        return repository;
    }
}
