package co.com.sofka.domain.bicycle.commands;

import co.com.sofka.domain.bicycle.values.*;
import co.com.sofka.domain.generic.Command;

public class CreateOrderCommand extends Command {
    private final BicycleId bicycleId;
    private final ClientId clientId;
    private final PropertyCardId propertyCardId;
    private final ContactDetail contactDetail;
    private final ClientRequest clientRequest;
    private final boolean requiredTeam;
    private final boolean requiredPerformance;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public ClientId getClienteId() {
        return clientId;
    }

    public PropertyCardId getPropertyCardId() {
        return propertyCardId;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public ClientRequest getClientRequest() {
        return clientRequest;
    }

    public boolean isRequiredTeam() {
        return requiredTeam;
    }

    public boolean isRequiredPerformance() {
        return requiredPerformance;
    }

    public CreateOrderCommand(BicycleId bicycleId, ClientId clientId, PropertyCardId propertyCardId, ContactDetail contactDetail,
                              ClientRequest clientRequest, boolean requiredTeam, boolean requiredPerformance) {
        this.bicycleId = bicycleId;
        this.clientId = clientId;
        this.propertyCardId = propertyCardId;
        this.contactDetail = contactDetail;
        this.clientRequest = clientRequest;
        this.requiredTeam = requiredTeam;
        this.requiredPerformance = requiredPerformance;
    }
}
