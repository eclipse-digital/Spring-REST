package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.eclipsegroup.spring_rest_workshops.java.Java;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String queryTemplate = "https://quickchart.io/chart?c={\"type\":\"bar\",\"data\":{\"labels\":[%s], \"datasets\":[{\"label\":\"Version\",\"data\":[%s]}]}}";

    public byte[] requestChart(List<Java> javaVersions) {
        ResponseEntity<byte[]> forObject = restTemplate.exchange(requestUri(javaVersions), HttpMethod.GET, null, byte[].class);
        return forObject.getBody();
    }

    private URI requestUri(List<Java> javaVersions) {
        return UriComponentsBuilder.fromUriString(String.format(queryTemplate, labelsFrom(javaVersions), dataFrom(javaVersions))).build().encode().toUri();
    }

    private String dataFrom(List<Java> javaVersions) {
        return javaVersions.stream().map(Java::getVersion).map(String::valueOf).collect(Collectors.joining(","));
    }

    private String labelsFrom(List<Java> javaVersions) {
        return javaVersions.stream().map(Java::getName).collect(Collectors.joining("\",\"", "\"", "\""));
    }
}
