package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.*;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.BarChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.ChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.LineChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<String, ChartRequestFactory> chartRequestFactories = Map.of(
            "bar", new BarChartRequestFactory(),
            "line", new LineChartRequestFactory()
    );

    public ResponseEntity<byte[]> requestChart(List<JavaVersion> javaVersions, String type) {
        var chartRequest = fromJavaVersions(javaVersions, type);
        return restTemplate.exchange("https://quickchart.io/chart", HttpMethod.POST, new HttpEntity<>(chartRequest, json()), byte[].class);
    }

    ChartRequest fromJavaVersions(List<JavaVersion> javaVersions, String type) {
        var chartRequestFactory = chartRequestFactories.get(type);
        if (chartRequestFactory != null) {
            return chartRequestFactory.create(javaVersions);
        } else {
            throw new IllegalArgumentException("Only `bar` or `line` chart types allowed, `" + type + "` given.");
        }
    }

    private ChartRequest chart(String bar, List<JavaVersion> javaVersions, List<Datasets> datasets) {
        return new ChartRequest(new Chart(bar, new Data(labelsFrom(javaVersions), datasets)));
    }

    private HttpHeaders json() {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private List<String> dataFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream().map(JavaVersion::getVersion).map(String::valueOf).collect(Collectors.toList());
    }

    private List<String> labelsFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream().map(JavaVersion::getName).collect(Collectors.toList());
    }
}
