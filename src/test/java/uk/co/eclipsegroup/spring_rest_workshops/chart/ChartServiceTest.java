package uk.co.eclipsegroup.spring_rest_workshops.chart;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Datasets;
import uk.co.eclipsegroup.spring_rest_workshops.java.Java;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SoftAssertionsExtension.class)
class ChartServiceTest {
    private final ChartService chartService = new ChartService();

    @Test
    void chartRequest_isCreated_forEmptyDataset() {
        var chartRequest = chartService.fromJavaVersions(List.of());

        assertThat(chartRequest.getChart().getType()).isEqualTo("bar");
    }

    @Test
    void chartRequest_contain_oneJavaVersion(SoftAssertions softly) {
        var chartRequest = chartService.fromJavaVersions(List.of(new Java("Java 1.0", 1.0)));

        softly.assertThat(chartRequest.getChart().getType()).isEqualTo("bar");
        softly.assertThat(chartRequest.getChart().getData().getDatasets())
                .containsExactly(new Datasets("Version", List.of("1.0")));
        softly.assertThat(chartRequest.getChart().getData().getLabels())
                .containsExactly("Java 1.0");
    }

    @Test
    void chartRequest_containsJavaVersions(SoftAssertions softly) {
        var chartRequest = chartService.fromJavaVersions(List.of(new Java("Java 1.0", 1.0), new Java("Java 1.1", 1.1)));

        softly.assertThat(chartRequest.getChart().getType()).isEqualTo("bar");
        softly.assertThat(chartRequest.getChart().getData().getDatasets())
                .containsExactly(new Datasets("Version", List.of("1.0", "1.1")));
        softly.assertThat(chartRequest.getChart().getData().getLabels())
                .containsExactly("Java 1.0", "Java 1.1");
    }
}