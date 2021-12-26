package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientRequest;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ContactDetail;
import co.com.sofka.domain.generic.Command;

public class CreateBicycleCommand extends Command {
    private final BicycleId bicycleId;
    private final ClientId clientId;
    private final ContactDetail contactDetail;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public ClientId getClientId() {
        return clientId;
    }


    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public CreateBicycleCommand(BicycleId bicycleId, ClientId clientId,  ContactDetail contactDetail) {
        this.bicycleId = bicycleId;
        this.clientId = clientId;
        this.contactDetail = contactDetail;
    }
}
