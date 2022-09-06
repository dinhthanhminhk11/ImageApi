package com.example.projectimage.model;

public class Weight {
    private String imperial;
    private String metric;

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "imperial='" + imperial + '\'' +
                ", metric='" + metric + '\'' +
                '}';
    }
}
