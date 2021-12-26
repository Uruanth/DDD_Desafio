package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.performance.values.PerformanceId;

public class ApproveProductCommand extends Command {

    private final BicycleId bicycleId;
    private final ClientId clientId;
    private final PerformanceId performanceId;

    public ApproveProductCommand(BicycleId bicycleId, ClientId clientId, PerformanceId performanceId) {
        this.bicycleId = bicycleId;
        this.clientId = clientId;
        this.performanceId = performanceId;
    }

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public PerformanceId getPerformanceId() {
        return performanceId;
    }
}
