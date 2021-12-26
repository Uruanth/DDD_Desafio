package co.com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.performance.values.PerformanceId;

public class AssignedPerformance extends DomainEvent {
    private final PerformanceId newPerformanceId;

    public PerformanceId getNewPerformanceId() {
        return newPerformanceId;
    }

    public AssignedPerformance(PerformanceId newPerformanceId) {
        super("sofka.bicycle.assignedPerformance");
        this.newPerformanceId = newPerformanceId;
    }
}
