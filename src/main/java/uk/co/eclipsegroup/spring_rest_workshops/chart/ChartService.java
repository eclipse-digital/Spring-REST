package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Chart;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.ChartRequest;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Data;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Datasets;
import uk.co.eclipsegroup.spring_rest_workshops.java.Java;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<byte[]> requestChart(List<Java> javaVersions) {
        var chartRequest = fromJavaVersions(javaVersions);
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return restTemplate.exchange("https://quickchart.io/chart", HttpMethod.POST, new HttpEntity<>(chartRequest, headers), byte[].class);
    }

    ChartRequest fromJavaVersions(List<Java> javaVersions) {
        return new ChartRequest(new Chart("bar", new Data(labelsFrom(javaVersions), List.of(new Datasets("Version", dataFrom(javaVersions))))));
    }

    private List<String> dataFrom(List<Java> javaVersions) {
        return javaVersions.stream().map(Java::getVersion).map(String::valueOf).collect(Collectors.toList());
    }

    private List<String> labelsFrom(List<Java> javaVersions) {
        return javaVersions.stream().map(Java::getName).collect(Collectors.toList());
    }
}
