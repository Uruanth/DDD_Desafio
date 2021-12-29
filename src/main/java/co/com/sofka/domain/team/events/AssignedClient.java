package co.com.sofka.domain.team.events;

import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ContactDetail;
import co.com.sofka.domain.generic.DomainEvent;

public class AssignedClient extends DomainEvent {
    private final ClientId clientId;
    private final ContactDetail contactDetail;

    public ClientId getClientId() {
        return clientId;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public AssignedClient(ClientId clientId, ContactDetail contactDetail) {
        super("sofka.bicycle.assignedClient");
        this.clientId = clientId;
        this.contactDetail = contactDetail;
    }
}
