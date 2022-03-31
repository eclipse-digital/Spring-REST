package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

import org.springframework.stereotype.Component;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.*;
import uk.co.eclipsegroup.spring_rest_workshops.java.JavaVersion;

import java.util.List;

@Component
public class BarChartRequestFactory extends AbstractChartRequestFactory {
    public BarChartRequestFactory() {
        super(ChartType.BAR);
    }

    @Override
    public ChartRequest create(List<JavaVersion> javaVersions) {
        return chart(javaVersions, List.of(new BarDatasets("Version", dataFrom(javaVersions))));
    }

}
