package co.com.sofka.domain.events;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.PilotId;
import co.com.sofka.domain.performance.values.QAId;

public class ApprovedProduct extends DomainEvent {
    private final BicycleId bicycleId;
    private final ClientId clientId;
    private final EngineerId engineerId;
    private final QAId qaId;
    private final PilotId pilotId;

    public ApprovedProduct(BicycleId bicycleId, ClientId clientId, EngineerId engineerId, QAId qaId, PilotId pilotId) {
        super("sofka.performance.approvedProduct");
        this.bicycleId = bicycleId;
        this.clientId = clientId;
        this.engineerId = engineerId;
        this.qaId = qaId;
        this.pilotId = pilotId;
    }

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public EngineerId getEnginnerId() {
        return engineerId;
    }

    public QAId getQaId() {
        return qaId;
    }

    public PilotId getPilotId() {
        return pilotId;
    }
}
