package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import org.springframework.stereotype.Component;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Chart;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.ChartRequest;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Data;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.LineDatasets;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

@Component
public class LineChartRequestFactory extends AbstractChartRequestFactory {
    public LineChartRequestFactory() {
        super("line");
    }

    @Override
    public ChartRequest createChart(List<JavaVersion> javaVersions) {
        return new ChartRequest(new Chart(type(), new Data(namesFrom(javaVersions), List.of(new LineDatasets("Version", valuesFrom(javaVersions), false, "pink")))));
    }
}
