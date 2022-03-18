package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JavaService {
    private final JavaRepository javaRepository;

    public JavaService(JavaRepository javaRepository) {
        this.javaRepository = javaRepository;
    }

    public List<Java> list() {
        return javaRepository.fetchAll();
    }
}
