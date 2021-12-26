package co.com.sofka.domain.bicycle;

import co.com.sofka.domain.bicycle.values.ClientRequest;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ContactDetail;
import co.com.sofka.domain.bicycle.values.Order;
import co.com.sofka.domain.generic.Entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Client extends Entity<ClientId> {
    protected ContactDetail contactDetail;
    protected Set<Order> orders;


    public Client(ClientId entityId, ContactDetail contactDetail, Set<Order> orders) {
        super(entityId);
        this.contactDetail = Objects.requireNonNull(contactDetail, "contactDetail cannot be null");
        this.orders = Objects.requireNonNull(orders, "orders cannot be null");
    }

    public Client(ClientId entityId, ContactDetail contactDetail) {
        super(entityId);
        this.contactDetail = Objects.requireNonNull(contactDetail, "contactDetail cannot be null");
        this.orders = new HashSet<Order>();
    }

    public void changeAddress(String newAddress){
        Objects.requireNonNull(newAddress, "newAddress cannot be null");
        contactDetail = new ContactDetail(contactDetail.value().name(), contactDetail.value().phonenumber(), newAddress);
    }

    public void changePhonenumber(String newPhonenumber){
        Objects.requireNonNull(newPhonenumber, "newPhonenumber cannot be null");
        contactDetail = new ContactDetail(contactDetail.value().name(), newPhonenumber, contactDetail.value().address());
    }

    public void addOrder(Order order){
        Objects.requireNonNull(order, "order cannot be null");
        if(orders.contains(order)) throw new IllegalArgumentException("Duplicate order");
        orders.add(order);
    }

    public Set<Order> orders() {
        return orders;
    }

    public ContactDetail contactDetail() {
        return contactDetail;
    }
}
