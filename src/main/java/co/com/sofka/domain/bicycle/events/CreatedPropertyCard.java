package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.bicycle.values.BicycleCharacteristics;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.EndorsedBy;
import co.com.sofka.domain.generic.DomainEvent;

public class CreatedPropertyCard extends DomainEvent {
    private final BicycleId identity;
    private final BicycleCharacteristics bicycleCharacteristics;
    private final EndorsedBy endorsedBy;

    public BicycleId getIdentity() {
        return identity;
    }

    public BicycleCharacteristics getBicycleCharacteristics() {
        return bicycleCharacteristics;
    }

    public EndorsedBy getEndorsedBy() {
        return endorsedBy;
    }

    public CreatedPropertyCard(BicycleId identity, BicycleCharacteristics bicycleCharacteristics, EndorsedBy endorsedBy) {
        super("sofka.bicycle.createdPropertyCard");
        this.identity = identity;
        this.bicycleCharacteristics = bicycleCharacteristics;
        this.endorsedBy = endorsedBy;
    }
}
