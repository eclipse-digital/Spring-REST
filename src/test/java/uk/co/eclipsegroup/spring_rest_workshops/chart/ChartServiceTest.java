package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Datasets;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

@ExtendWith(SoftAssertionsExtension.class)
class ChartServiceTest {
    private final ChartService chartService = new ChartService();

    @Test
    void emptyChartRequest_isCreated_forEmptyDataset(SoftAssertions softly) {
        var chartRequest = chartService.fromJavaVersions(List.of(), "bar");

        softly.assertThat(chartRequest.getChart().getType()).isEqualTo("bar");
        softly.assertThat(chartRequest.getChart().getData().getLabels()).isEmpty();
        softly.assertThat(chartRequest.getChart().getData().getDatasets()).flatExtracting(Datasets::getData).isEmpty();
    }

    @Test
    void chartRequest_contains_oneJavaVersion(SoftAssertions softly) {
        var chartRequest = chartService.fromJavaVersions(List.of(new JavaVersion("Java 1.0", 1.0)), "line");

        softly.assertThat(chartRequest.getChart().getType()).isEqualTo("line");
        softly.assertThat(chartRequest.getChart().getData().getDatasets())
                .containsExactly(new Datasets("Version", List.of("1.0")));
        softly.assertThat(chartRequest.getChart().getData().getLabels())
                .containsExactly("Java 1.0");
    }

    @Test
    void chartRequest_contains_javaVersions(SoftAssertions softly) {
        var chartRequest = chartService.fromJavaVersions(List.of(new JavaVersion("Java 1.0", 1.0), new JavaVersion("Java 1.1", 1.1)), "bar");

        softly.assertThat(chartRequest.getChart().getType()).isEqualTo("bar");
        softly.assertThat(chartRequest.getChart().getData().getDatasets())
                .containsExactly(new Datasets("Version", List.of("1.0", "1.1")));
        softly.assertThat(chartRequest.getChart().getData().getLabels())
                .containsExactly("Java 1.0", "Java 1.1");
    }
}