package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.co.eclipsegroup.spring_rest_workshops.chart.ChartService;

import java.util.List;

@Service
public class JavaVersionService {
    private final JavaVersionRepository javaVersionRepository;
    private final ChartService chartService;

    public JavaVersionService(JavaVersionRepository javaVersionRepository, ChartService chartService) {
        this.javaVersionRepository = javaVersionRepository;
        this.chartService = chartService;
    }

    public List<JavaVersion> list() {
        return javaVersionRepository.fetchAll();
    }

    public void store(List<JavaVersion> javaVersions) {
        javaVersions.forEach(javaVersionRepository::save);
    }

    public void delete(String javaVersion) {
        javaVersionRepository.remove(javaVersion);
    }

    public ResponseEntity<byte[]> toChart() {
        return chartService.requestChart(javaVersionRepository.fetchAll());
    }
}
