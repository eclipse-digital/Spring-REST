package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<byte[]> createChart(List<JavaVersion> javaVersions) {
        var javaVersionNames = names(javaVersions);

        var javaVersionValues = values(javaVersions);

        return requestChart(javaVersionNames, javaVersionValues);
    }

    private String values(List<JavaVersion> javaVersions) {
        return javaVersions.stream()
                .map(JavaVersion::getVersion)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    private String names(List<JavaVersion> javaVersions) {
        return javaVersions.stream()
                .map(JavaVersion::getName)
                .map(name -> "'" + name + "'")
                .collect(Collectors.joining(","));
    }

    private ResponseEntity<byte[]> requestChart(String javaVersionNames, String javaVersionValues) {
        try {
            var encodedQuery = URLEncoder.encode("{type:'bar',data:{labels:[" + javaVersionNames + "], datasets:[{label:'Version',data:[" + javaVersionValues + "]}]}}", StandardCharsets.UTF_8.toString());
            return restTemplate.exchange(new URI("https://quickchart.io/chart?c=" + encodedQuery), HttpMethod.GET, null, byte[].class);
        } catch (UnsupportedEncodingException | URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }
}
