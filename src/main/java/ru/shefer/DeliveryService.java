package ru.shefer;

public class DeliveryService {
    private Workload workload;

    public DeliveryService(Workload workload) {
        this.workload = workload;
    }

    public Workload getWorkload() {
        return workload;
    }

    public void setWorkload(Workload workload) {
        this.workload = workload;
    }
}
