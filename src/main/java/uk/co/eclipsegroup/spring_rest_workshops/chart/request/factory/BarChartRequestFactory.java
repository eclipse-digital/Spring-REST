package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import uk.co.eclipsegroup.spring_rest_workshops.chart.request.*;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

public class BarChartRequestFactory extends AbstractChartRequestFactory {
    @Override
    public ChartRequest create(List<JavaVersion> javaVersions) {
        return chart("bar", javaVersions, List.of(new BarDatasets("Version", dataFrom(javaVersions))));
    }
}
