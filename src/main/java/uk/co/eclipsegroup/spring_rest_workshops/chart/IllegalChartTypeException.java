package uk.co.eclipsegroup.spring_rest_workshops.chart;

import uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory.ChartRequestFactory;

import java.util.List;
import java.util.stream.Collectors;

public class IllegalChartTypeException extends IllegalArgumentException {
    public IllegalChartTypeException(List<ChartRequestFactory> chartRequestFactories, String chartType) {
        super("Only " + types(chartRequestFactories) + " chart types supported `" + chartType + "` given");
    }

    private static String types(List<ChartRequestFactory> chartRequestFactories) {
        return chartRequestFactories.stream()
                .map(ChartRequestFactory::type)
                .collect(Collectors.joining(",", "`", "`"));
    }
}
