package uk.co.eclipsegroup.spring_rest_workshops.java;

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
    public void addJavaVersion(@RequestBody Java java) {
        javaService.store(java);
    }

    @DeleteMapping("{javaVersion}")
    public void removeJavaVersion(@PathVariable String javaVersion) {
        javaService.delete(javaVersion);
    }

}
