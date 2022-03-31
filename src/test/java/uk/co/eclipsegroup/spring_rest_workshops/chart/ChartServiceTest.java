package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.BarDatasets;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Datasets;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.LineDatasets;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.BarChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.LineChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SoftAssertionsExtension.class)
class ChartServiceTest {
    private final ChartService chartService = new ChartService(List.of(new BarChartRequestFactory(), new LineChartRequestFactory()));

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
                .containsExactly(new LineDatasets("Version", List.of("1.0"), false, "pink"));
        softly.assertThat(chartRequest.getChart().getData().getLabels())
                .containsExactly("Java 1.0");
    }

    @Test
    void chartRequest_contains_javaVersions(SoftAssertions softly) {
        var chartRequest = chartService.fromJavaVersions(List.of(new JavaVersion("Java 1.0", 1.0), new JavaVersion("Java 1.1", 1.1)), "bar");

        softly.assertThat(chartRequest.getChart().getType()).isEqualTo("bar");
        softly.assertThat(chartRequest.getChart().getData().getDatasets())
                .containsExactly(new BarDatasets("Version", List.of("1.0", "1.1")));
        softly.assertThat(chartRequest.getChart().getData().getLabels())
                .containsExactly("Java 1.0", "Java 1.1");
    }

    @Test
    void cannotCreate_chartOtherThan_barOrLine() {
        assertThatCode(() -> chartService.fromJavaVersions(List.of(), "")).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}