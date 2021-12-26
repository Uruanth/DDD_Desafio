package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.PropertyCardId;
import co.com.sofka.domain.generic.Command;

public class CancelAnOrderCommand extends Command {
    private final BicycleId bicycleId;
    private final ClientId clientId;
    private final PropertyCardId propertyCardId;
    private final String specifications;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public ClientId getClienteId() {
        return clientId;
    }

    public PropertyCardId getPropertyCardId() {
        return propertyCardId;
    }

    public String getSpecifications() {
        return specifications;
    }

    public CancelAnOrderCommand(BicycleId bicycleId, ClientId clientId, PropertyCardId propertyCardId, String specifications) {
        this.bicycleId = bicycleId;
        this.clientId = clientId;
        this.propertyCardId = propertyCardId;
        this.specifications = specifications;
    }
}
