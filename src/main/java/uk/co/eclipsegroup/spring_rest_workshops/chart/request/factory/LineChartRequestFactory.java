package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import org.springframework.stereotype.Component;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.*;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

@Component
public class LineChartRequestFactory extends AbstractChartRequestFactory {
    @Override
    public ChartRequest create(List<JavaVersion> javaVersions) {
        return chart(javaVersions, List.of(new LineDatasets("Version", dataFrom(javaVersions), false, "pink")));
    }

    @Override
    public String type() {
        return "line";
    }
}
