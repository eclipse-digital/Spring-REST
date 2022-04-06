package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.ChartRequest;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.BarChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.ChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.LineChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final List<ChartRequestFactory> chartRequestFactories;

    public ChartService(List<ChartRequestFactory> chartRequestFactories) {
        this.chartRequestFactories = chartRequestFactories;
    }

    public ResponseEntity<byte[]> createChart(List<JavaVersion> javaVersions, String chartType) {
        HttpEntity<ChartRequest> httpRequest = httpRequest(javaVersions, chartType);
        return restTemplate.exchange("https://quickchart.io/chart", HttpMethod.POST, httpRequest, byte[].class);
    }

    private HttpEntity<ChartRequest> httpRequest(List<JavaVersion> javaVersions, String chartType) {
        var chartRequest = fromJavaVersions(javaVersions, chartType);
        return new HttpEntity<>(chartRequest, json());
    }

    private HttpHeaders json() {
        var httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

    ChartRequest fromJavaVersions(List<JavaVersion> javaVersions, String chartType) {
        return chartRequestFactories.stream()
                .filter(f -> f.type().equals(chartType))
                .findFirst()
                .map(f -> f.createChart(javaVersions))
                .orElseThrow(() -> new IllegalChartTypeException(chartRequestFactories, chartType));
    }
}
