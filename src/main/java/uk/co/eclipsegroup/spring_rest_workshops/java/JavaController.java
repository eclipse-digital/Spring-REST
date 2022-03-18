package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
