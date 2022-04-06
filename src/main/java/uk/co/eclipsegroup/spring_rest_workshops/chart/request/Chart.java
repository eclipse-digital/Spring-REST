package uk.co.eclipsegroup.spring_rest_workshops.chart.request;

import java.util.Objects;

public class Chart {
    private final String type;
    private final Data data;

    public Chart(String type, Data data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public Data getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chart chart = (Chart) o;
        return Objects.equals(type, chart.type) && Objects.equals(data, chart.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, data);
    }

    @Override
    public String toString() {
        return "Chart{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
