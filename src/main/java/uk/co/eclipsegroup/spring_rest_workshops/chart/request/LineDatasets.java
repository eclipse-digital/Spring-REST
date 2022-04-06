package uk.co.eclipsegroup.spring_rest_workshops.chart.request;

import java.util.List;
import java.util.Objects;

public class LineDatasets implements Datasets {
    private final String label;
    private final List<String> data;
    private final Boolean fill;
    private final String borderColor;

    public LineDatasets(String label, List<String> data, Boolean fill, String borderColor) {
        this.label = label;
        this.data = data;
        this.fill = fill;
        this.borderColor = borderColor;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public List<String> getData() {
        return data;
    }

    public Boolean getFill() {
        return fill;
    }

    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineDatasets datasets = (LineDatasets) o;
        return Objects.equals(label, datasets.label) && Objects.equals(data, datasets.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, data);
    }

    @Override
    public String toString() {
        return "Datasets{" +
                "label='" + label + '\'' +
                ", data=" + data +
                '}';
    }
}
