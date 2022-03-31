package uk.co.eclipsegroup.spring_rest_workshops.chart.request.factory;

public enum ChartType {
    BAR, LINE;

    public String chartTypeName() {
        return name().toLowerCase();
    }
}
