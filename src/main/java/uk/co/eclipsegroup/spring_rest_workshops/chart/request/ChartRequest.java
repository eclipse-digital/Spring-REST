package uk.co.eclipsegroup.spring_rest_workshops.chart.request;

import java.util.Objects;

public class ChartRequest {
    private final Chart chart;

    public ChartRequest(Chart chart) {
        this.chart = chart;
    }

    public Chart getChart() {
        return chart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartRequest that = (ChartRequest) o;
        return Objects.equals(chart, that.chart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chart);
    }

    @Override
    public String toString() {
        return "ChartRequest{" +
                "chart=" + chart +
                '}';
    }
}
