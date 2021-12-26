package co.com.sofka.domain.events;

import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ClientRequest;
import co.com.sofka.domain.bicycle.values.Order;
import co.com.sofka.domain.bicycle.values.PropertyCardId;
import co.com.sofka.domain.generic.DomainEvent;

public class ChangedOrder extends DomainEvent {
    private final ClientId clientId;
    private final PropertyCardId propertyCardId;
    private final ClientRequest specifications;
    private final Order order;

    public ClientId getClientId() {
        return clientId;
    }

    public PropertyCardId getPropertyCardId() {
        return propertyCardId;
    }

    public ClientRequest getSpecifications() {
        return specifications;
    }

    public ChangedOrder(ClientId clientId, PropertyCardId propertyCardId, ClientRequest specifications, Order order) {
        super("sofka.bicycle.changedOrder");
        this.clientId = clientId;
        this.propertyCardId = propertyCardId;
        this.specifications = specifications;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
