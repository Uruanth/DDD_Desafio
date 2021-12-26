package co.com.sofka.domain.events;

import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.generic.DomainEvent;

public class CanceledOrder extends DomainEvent {
    private final ClientId clientId;

    public ClientId getClientId() {
        return clientId;
    }

    public CanceledOrder(ClientId clientId) {
        super("sofka.bicycle.canceledOrder");
        this.clientId = clientId;
    }
}
