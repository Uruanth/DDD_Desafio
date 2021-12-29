package co.com.sofka.domain.bicycle.commands;

import co.com.sofka.domain.bicycle.values.*;
import co.com.sofka.domain.generic.Command;

public class ChangeOrderCommand extends Command {
    private final BicycleId bicycleId;
    private final Order oldOrder;
    private final ClientId clientId;
    private final PropertyCardId propertyCardId;
    private final ClientRequest specifications;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public Order getOldOrder() {
        return oldOrder;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public PropertyCardId getPropertyCardId() {
        return propertyCardId;
    }


    public ClientRequest getSpecifications() {
        return specifications;
    }

    public ChangeOrderCommand(BicycleId bicycleId, Order oldOrder, ClientId clientId, PropertyCardId propertyCardId, ClientRequest specifications) {
        this.bicycleId = bicycleId;
        this.oldOrder = oldOrder;
        this.clientId = clientId;
        this.propertyCardId = propertyCardId;
        this.specifications = specifications;
    }
}
