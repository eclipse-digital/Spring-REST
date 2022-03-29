package uk.co.eclipsegroup.spring_rest_workshops.chart.request;

import java.util.List;
import java.util.Objects;

public class Data {
    private final List<String> labels;
    private final List<Datasets> datasets;

    public Data(List<String> labels, List<Datasets> datasets) {
        this.labels = labels;
        this.datasets = datasets;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Datasets> getDatasets() {
        return datasets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return Objects.equals(labels, data.labels) && Objects.equals(datasets, data.datasets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labels, datasets);
    }

    @Override
    public String toString() {
        return "Data{" +
                "labels=" + labels +
                ", datasets=" + datasets +
                '}';
    }
}
