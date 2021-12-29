package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ClientRequest;
import co.com.sofka.domain.bicycle.values.PropertyCardId;
import co.com.sofka.domain.generic.DomainEvent;

public class CreatedOrder extends DomainEvent {
    private final ClientId clientId;
    private final PropertyCardId propertyCardId;
    private final ClientRequest specifications;

    public ClientId getClientId() {
        return clientId;
    }

    public PropertyCardId getPropertyCardId() {
        return propertyCardId;
    }

    public ClientRequest getSpecifications() {
        return specifications;
    }

    public CreatedOrder(ClientId clientId, PropertyCardId propertyCardId, ClientRequest specifications) {
        super("sofka.bicycle.createdOrder");
        this.clientId = clientId;
        this.propertyCardId = propertyCardId;
        this.specifications = specifications;
    }
}
