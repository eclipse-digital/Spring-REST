package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import uk.co.eclipsegroup.spring_rest_workshops.chart.request.BarDatasets;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Chart;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.ChartRequest;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Data;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

public class BarChartRequestFactory extends AbstractChartRequestFactory {
    @Override
    public ChartRequest createChart(List<JavaVersion> javaVersions) {
        return new ChartRequest(new Chart("bar", new Data(namesFrom(javaVersions), List.of(new BarDatasets("Version", valuesFrom(javaVersions))))));
    }
}
