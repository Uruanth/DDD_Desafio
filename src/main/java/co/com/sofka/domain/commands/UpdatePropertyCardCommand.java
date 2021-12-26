package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.PropertyCardId;
import co.com.sofka.domain.generic.Command;

public class UpdatePropertyCardCommand extends Command {
    private final BicycleId bicycleId;
    private final PropertyCardId propertyCardId;
    private final String owner;
    private final String color;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public PropertyCardId getPropertyCardId() {
        return propertyCardId;
    }

    public String getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }

    public UpdatePropertyCardCommand(BicycleId bicycleId, PropertyCardId propertyCardId, String owner, String color) {
        this.bicycleId = bicycleId;
        this.propertyCardId = propertyCardId;
        this.owner = owner;
        this.color = color;
    }
}
