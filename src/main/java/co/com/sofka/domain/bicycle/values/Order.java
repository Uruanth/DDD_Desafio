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

    public interface Properties{
        ClientId clientId();
        PropertyCardId propertyCardId();
        ClientRequest specifications();
        boolean status();
    }
}
