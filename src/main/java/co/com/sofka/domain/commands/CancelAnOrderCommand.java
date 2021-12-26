package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.Order;
import co.com.sofka.domain.bicycle.values.PropertyCardId;
import co.com.sofka.domain.generic.Command;

public class CancelAnOrderCommand extends Command {
    private final BicycleId bicycleId;
    private final Order order;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public Order getOrder() {
        return order;
    }

    public CancelAnOrderCommand(BicycleId bicycleId, Order order) {
        this.bicycleId = bicycleId;
        this.order = order;
    }
}
