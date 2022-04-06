package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractChartRequestFactory implements ChartRequestFactory{
    List<String> valuesFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream()
                .map(JavaVersion::getVersion)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    List<String> namesFrom(List<JavaVersion> javaVersions) {
        return javaVersions.stream()
                .map(JavaVersion::getName)
                .collect(Collectors.toList());
    }
}
