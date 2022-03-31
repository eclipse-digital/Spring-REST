package uk.co.eclipsegroup.spring_rest_workshops.chart;

import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.ChartRequestFactory;
import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.ChartType;

import java.util.List;

public class ChartRequestFactoryNotFoundException extends IllegalArgumentException{
    public ChartRequestFactoryNotFoundException(ChartType type, List<ChartRequestFactory> chartRequestFactories) {
        super("Could not find a chart factory for `" + type + "`. Available chart factories: " + chartRequestFactories);
    }
}
