package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("java")
public class JavaVersionController {
    private final JavaVersionService javaVersionService;

    public JavaVersionController(JavaVersionService javaVersionService) {
        this.javaVersionService = javaVersionService;
    }

    @GetMapping
    public List<JavaVersion> list() {
        return javaVersionService.getAll();
    }

    @PostMapping
    public void addJavaVersion(@RequestBody List<JavaVersion> javaVersions) {
        javaVersionService.store(javaVersions);
    }
}
