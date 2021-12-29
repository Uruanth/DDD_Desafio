package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.bicycle.values.BikeSpecifications;
import co.com.sofka.domain.bicycle.values.MachineryId;
import co.com.sofka.domain.generic.DomainEvent;

public class SavedMachinary extends DomainEvent {
    private final MachineryId machineryId;
    private final BikeSpecifications bikeSpecifications;

    public MachineryId getMachineryId() {
        return machineryId;
    }

    public BikeSpecifications getBikeSpecifications() {
        return bikeSpecifications;
    }

    public SavedMachinary(MachineryId machineryId, BikeSpecifications bikeSpecifications) {
        super("sofka.bicycle.SavedMachinary");
        this.machineryId = machineryId;
        this.bikeSpecifications = bikeSpecifications;
    }
}
