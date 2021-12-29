package co.com.sofka.domain.bicycle.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.performance.values.PerformanceId;

public class ChangePerformanceCommand extends Command {
    private final BicycleId bicycleId;
    private final PerformanceId performanceId;

    public ChangePerformanceCommand(BicycleId bicycleId, PerformanceId performanceId) {
        this.bicycleId = bicycleId;
        this.performanceId = performanceId;
    }

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public PerformanceId getPerformanceId() {
        return performanceId;
    }
}
