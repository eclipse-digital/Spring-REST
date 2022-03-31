package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Chart;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.ChartRequest;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Data;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.Datasets;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractChartRequestFactory implements ChartRequestFactory{
    protected ChartRequest chart(List<JavaVersion> javaVersions, List<Datasets> datasets) {
        return new ChartRequest(new Chart(type(), new Data(labelsFrom(javaVersions), datasets)));
    }

    protected List<String> dataFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream().map(JavaVersion::getVersion).map(String::valueOf).collect(Collectors.toList());
    }

    List<String> labelsFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream().map(JavaVersion::getName).collect(Collectors.toList());
    }

    @Override
    public boolean supports(String type) {
        return type.equals(type());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + type();
    }
}
