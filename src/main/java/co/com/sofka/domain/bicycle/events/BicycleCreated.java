package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.bicycle.values.ClientRequest;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ContactDetail;
import co.com.sofka.domain.generic.DomainEvent;

public class BicycleCreated extends DomainEvent {
    private final ClientId clientId;
    private final ContactDetail contactDetail;

    public ClientId getClientId() {
        return clientId;
    }


    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public BicycleCreated(ClientId clientId,  ContactDetail contactDetail) {
        super("sofka.bicycle.BicycleCreated");
        this.clientId = clientId;
        this.contactDetail = contactDetail;
    }
}
