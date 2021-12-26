package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleCharacteristics;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.EndorsedBy;
import co.com.sofka.domain.generic.Command;

public class CreatePropertyCardCommand extends Command {
    private final BicycleId bicycleId;
    private final BicycleCharacteristics bicycleCharacteristics;
    private final EndorsedBy endorsedBy;

    public CreatePropertyCardCommand(BicycleId bicycleId, BicycleCharacteristics bicycleCharacteristics, EndorsedBy endorsedBy) {
        this.bicycleId = bicycleId;
        this.bicycleCharacteristics = bicycleCharacteristics;
        this.endorsedBy = endorsedBy;
    }

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public BicycleCharacteristics getBicycleCharacteristics() {
        return bicycleCharacteristics;
    }

    public EndorsedBy getEndorsedBy() {
        return endorsedBy;
    }
}
