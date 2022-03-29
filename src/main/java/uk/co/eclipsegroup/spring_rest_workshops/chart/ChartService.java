package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<byte[]> createChart(List<JavaVersion> javaVersions) {
        var javaVersionNames = javaVersions.stream()
                .map(JavaVersion::getName)
                .collect(Collectors.joining(",", "'", "'"));

        var javaVersionValues = javaVersions.stream()
                .map(JavaVersion::getVersion)
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        var uri = UriComponentsBuilder.fromUriString("https://quickchart.io/chart?c={type:'bar',data:{labels:[" + javaVersionNames + "], datasets:[{label:'Version',data:[" + javaVersionValues + "]}]}}")
                .build().encode().toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, null, byte[].class);
    }
}
