package co.com.sofka.domain.bicycle;

import co.com.sofka.domain.bicycle.values.BikeSpecifications;
import co.com.sofka.domain.bicycle.values.MachineryId;
import co.com.sofka.domain.generic.Entity;

import java.util.Objects;

public class Machinary extends Entity<MachineryId> {
    protected BikeSpecifications bikeSpecifications;

    public Machinary(MachineryId machineryId, BikeSpecifications bikeSpecifications) {
        super(machineryId);
        this.bikeSpecifications = bikeSpecifications;
    }

    public void changeStructure(String newStructure) {
        Objects.requireNonNull(newStructure, "newStructure cannot be null");
        bikeSpecifications = new BikeSpecifications(newStructure, bikeSpecifications.value().tires(),bikeSpecifications.value().brakes());
    }

    public void changeTires(Double newTires) {
        Objects.requireNonNull(newTires, "newTires cannot be null");
        bikeSpecifications = new BikeSpecifications(bikeSpecifications.value().structure(), newTires, bikeSpecifications.value().brakes());
    }
    public void changeBrakes(String newBrakes) {
        Objects.requireNonNull(newBrakes, "newStructure cannot be null");
        bikeSpecifications = new BikeSpecifications(bikeSpecifications.value().structure(), bikeSpecifications.value().tires(),newBrakes);
    }

    public BikeSpecifications bikeSpecifications() {
        return bikeSpecifications;
    }
}
