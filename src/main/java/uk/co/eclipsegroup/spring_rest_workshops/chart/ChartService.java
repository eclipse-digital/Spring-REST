package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.ChartRequest;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.ChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final List<ChartRequestFactory> chartRequestFactories;

    public ChartService(List<ChartRequestFactory> chartRequestFactories) {
        this.chartRequestFactories = chartRequestFactories;
    }

    public ResponseEntity<byte[]> requestChart(List<JavaVersion> javaVersions, String type) {
        var chartRequest = fromJavaVersions(javaVersions, type);
        return restTemplate.exchange("https://quickchart.io/chart", HttpMethod.POST, new HttpEntity<>(chartRequest, json()), byte[].class);
    }

    ChartRequest fromJavaVersions(List<JavaVersion> javaVersions, String type) {
        return chartRequestFactories.stream()
                .filter(f -> f.supports(type))
                .map(f -> f.create(javaVersions))
                .findFirst()
                .orElseThrow(() -> new ChartRequestFactoryNotFoundException(type, chartRequestFactories));
    }

    private HttpHeaders json() {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
