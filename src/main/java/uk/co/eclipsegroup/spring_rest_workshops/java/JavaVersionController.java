package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public List<JavaVersion> javaVersions() {
        return javaVersionService.list();
    }

    @PostMapping
    public void addJavaVersion(@RequestBody List<JavaVersion> javaVersion) {
        javaVersionService.store(javaVersion);
    }

    @DeleteMapping("{javaVersion}")
    public void removeJavaVersion(@PathVariable String javaVersion) {
        javaVersionService.delete(javaVersion);
    }

    @GetMapping(value = "chart", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> chart() {
        return javaVersionService.toChart();
    }

}
