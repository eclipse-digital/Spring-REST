package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.*;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import javax.sound.sampled.Line;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();

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
        if (chartType.equals("bar")) {
            return new ChartRequest(new Chart(chartType, new Data(namesFrom(javaVersions), List.of(new BarDatasets("Version", valuesFrom(javaVersions))))));
        } else if (chartType.equals("line")) {
            return new ChartRequest(new Chart(chartType, new Data(namesFrom(javaVersions), List.of(new LineDatasets("Version", valuesFrom(javaVersions), false, "pink")))));
        } else {
            throw new IllegalArgumentException("Only `bar` and `line` chart types supported `" + chartType + "`");
        }
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
