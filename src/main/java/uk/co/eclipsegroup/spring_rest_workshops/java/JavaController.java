package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("java")
public class JavaController {
    private final JavaService javaService;

    public JavaController(JavaService javaService) {
        this.javaService = javaService;
    }

    @GetMapping
    public List<Java> javaVersions() {
        return javaService.list();
    }

    @PostMapping
    public void addJavaVersion(@RequestBody List<Java> java) {
        javaService.store(java);
    }

    @DeleteMapping("{javaVersion}")
    public void removeJavaVersion(@PathVariable String javaVersion) {
        javaService.delete(javaVersion);
    }

    @GetMapping(value = "chart", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> chart() {
        return javaService.toChart();
    }

}
