package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.*;
import co.com.sofka.domain.generic.Command;

import java.util.Set;

public class SaveClientCommand extends Command {
    private final BicycleId bicycleId;
    private final ClientId clientId;
    private final ContactDetail contactDetail;
    private final Set<Order> orders;


    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public ClientId getClientId() {
        return clientId;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public SaveClientCommand(BicycleId bicycleId, ClientId clientId, ContactDetail contactDetail, Set<Order> orders) {
        this.bicycleId = bicycleId;
        this.clientId = clientId;
        this.contactDetail = contactDetail;
        this.orders = orders;
    }







}
