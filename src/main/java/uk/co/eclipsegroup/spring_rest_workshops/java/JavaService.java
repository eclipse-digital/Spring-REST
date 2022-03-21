package uk.co.eclipsegroup.spring_rest_workshops.java;

import org.springframework.stereotype.Service;
import uk.co.eclipsegroup.spring_rest_workshops.chart.ChartService;

import java.util.List;

@Service
public class JavaService {
    private final JavaRepository javaRepository;
    private final ChartService chartService;

    public JavaService(JavaRepository javaRepository, ChartService chartService) {
        this.javaRepository = javaRepository;
        this.chartService = chartService;
    }

    public List<Java> list() {
        return javaRepository.fetchAll();
    }

    public void store(List<Java> javaVersions) {
        javaVersions.forEach(javaRepository::save);
    }

    public void delete(String javaVersion) {
        javaRepository.remove(javaVersion);
    }

    public byte[] toChart() {
        return chartService.requestChart(javaRepository.fetchAll());
    }
}
