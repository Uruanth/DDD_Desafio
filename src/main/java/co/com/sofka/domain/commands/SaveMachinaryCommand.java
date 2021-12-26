package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.BikeSpecifications;
import co.com.sofka.domain.generic.Command;

public class SaveMachinaryCommand extends Command {
    private final BicycleId bicycleId;
    private final BikeSpecifications bikeSpecifications;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public BikeSpecifications getBikeSpecifications() {
        return bikeSpecifications;
    }

    public SaveMachinaryCommand(BicycleId bicycleId, BikeSpecifications bikeSpecifications) {
        this.bicycleId = bicycleId;
        this.bikeSpecifications = bikeSpecifications;
    }
}
