package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import uk.co.eclipsegroup.spring_rest_workshops.chart.request.ChartRequest;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

public interface ChartRequestFactory {
    ChartRequest create(List<JavaVersion> javaVersions);

    boolean supports(ChartType type);
}
