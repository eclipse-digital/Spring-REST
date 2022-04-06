package uk.co.eclipsegroup.spring_rest_workshops.chart.request;

import java.util.List;

public interface Datasets {
    String getLabel();

    List<String> getData();

}
