package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.Order;
import co.com.sofka.domain.generic.DomainEvent;

public class CanceledOrder extends DomainEvent {
    private final Order order;


    public CanceledOrder(Order order) {
        super("sofka.bicycle.canceledOrder");
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
