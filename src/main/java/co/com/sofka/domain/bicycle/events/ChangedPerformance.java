package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.performance.values.PerformanceId;

public class ChangedPerformance extends DomainEvent {
    private final PerformanceId newPerformanceId;

    public PerformanceId getNewPerformanceId() {
        return newPerformanceId;
    }

    public ChangedPerformance(PerformanceId newPerformanceId) {
        super("sofka.bicycle.changedPerformance");
        this.newPerformanceId = newPerformanceId;
    }
}
