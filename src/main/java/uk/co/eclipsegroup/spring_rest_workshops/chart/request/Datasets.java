package uk.co.eclipsegroup.spring_rest_workshops.chart.request;

import java.util.List;
import java.util.Objects;

public class Datasets {
    private final String label;
    private final List<String> data;

    public Datasets(String label, List<String> data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datasets datasets = (Datasets) o;
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
