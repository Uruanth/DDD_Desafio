package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Order implements ValueObject<Order.Properties> {
    private final ClientId clientId;
    private final PropertyCardId propertyCardId;
    private final ClientRequest specifications;
    private final boolean status;

    public Order(ClientId clientId, PropertyCardId propertyCardId, ClientRequest specifications, boolean status) {
        this.clientId = Objects.requireNonNull(clientId, "clientId cannot be null");
        this.propertyCardId = Objects.requireNonNull(propertyCardId, "propertyCardId cannot be null");
        this.specifications = Objects.requireNonNull(specifications, "specifications cannot be null");
        this.status = Objects.requireNonNull(status, "status cannot be null");
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", propertyCardId=" + propertyCardId +
                ", specifications=" + specifications +
                ", status=" + status +
                '}';
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public ClientId clientId() {
                return clientId;
            }

            @Override
            public PropertyCardId propertyCardId() {
                return propertyCardId;
            }

            @Override
            public ClientRequest specifications() {
                return specifications;
            }

            @Override
            public boolean status() {
                return status;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (status != order.status) return false;
        if (clientId != null ? !clientId.equals(order.clientId) : order.clientId != null) return false;
        if (propertyCardId != null ? !propertyCardId.equals(order.propertyCardId) : order.propertyCardId != null)
            return false;
        return specifications != null ? specifications.equals(order.specifications) : order.specifications == null;
    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + (propertyCardId != null ? propertyCardId.hashCode() : 0);
        result = 31 * result + (specifications != null ? specifications.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    public interface Properties{
        ClientId clientId();
        PropertyCardId propertyCardId();
        ClientRequest specifications();
        boolean status();
    }
}
