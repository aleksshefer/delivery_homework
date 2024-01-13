package ru.shefer;

public enum Workload {
    VERY_BUSY(1.6),
    BUSY(1.4),
    INCREASED(1.2),
    NORMAL(1.0);
    private final double loadFactor;

    Workload(double loadFactor) {
        this.loadFactor = loadFactor;
    }

    public static Workload getWorkload(double load) {
        if (Double.compare(load, 1.6) == 0) {
            return VERY_BUSY;
        } else if (Double.compare(load, 1.4) == 0) {
            return BUSY;
        } else if (Double.compare(load, 1.2) == 0) {
            return INCREASED;
        } else if (Double.compare(load, 1) == 0) {
            return NORMAL;
        } else return null;
    }

    public double getLoadFactor() {
        return loadFactor;
    }
}
