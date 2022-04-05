package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.*;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<byte[]> createChart(List<JavaVersion> javaVersions) {
        HttpEntity<ChartRequest> httpRequest = httpRequest(javaVersions);
        return restTemplate.exchange("https://quickchart.io/chart", HttpMethod.POST, httpRequest, byte[].class);
    }

    private HttpEntity<ChartRequest> httpRequest(List<JavaVersion> javaVersions) {
        var chartRequest = fromJavaVersions(javaVersions);
        return new HttpEntity<>(chartRequest, json());
    }

    private HttpHeaders json() {
        var httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

    ChartRequest fromJavaVersions(List<JavaVersion> javaVersions) {
        return new ChartRequest(new Chart("bar", new Data(namesFrom(javaVersions), List.of(new BarDatasets("Version", valuesFrom(javaVersions))))));
    }

    private List<String> valuesFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream()
                .map(JavaVersion::getVersion)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> namesFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream()
                .map(JavaVersion::getName)
                .collect(Collectors.toList());
    }
}
