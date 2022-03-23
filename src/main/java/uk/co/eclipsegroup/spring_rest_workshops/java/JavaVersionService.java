package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JavaVersionService {
    private final JavaVersionRepository javaVersionRepository;

    public JavaVersionService(JavaVersionRepository javaVersionRepository) {
        this.javaVersionRepository = javaVersionRepository;
    }

    public List<JavaVersion> getAll() {
        return javaVersionRepository.fetchAll();
    }

    public void store(JavaVersion javaVersion) {
        javaVersionRepository.save(javaVersion);
    }
}
