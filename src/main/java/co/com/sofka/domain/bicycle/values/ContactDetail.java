package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class ContactDetail implements ValueObject<ContactDetail.Properties> {
    private final String name;
    private final String phonenumber;
    private final String address;

    public ContactDetail(String name, String phonenumber, String address) {
        if (name.isBlank()) throw new IllegalArgumentException("name cannot be empty");
        if(phonenumber.isBlank()) throw new IllegalArgumentException("phonenumber cannot be empty");
        if(address.isBlank()) throw new IllegalArgumentException("address cannot be empty");

        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.phonenumber = Objects.requireNonNull(phonenumber, "phonenumber cannot be null");
        this.address = Objects.requireNonNull(address, "address cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public String phonenumber() {
                return phonenumber;
            }

            @Override
            public String address() {
                return address;
            }
        };
    }


    public interface Properties {
        String name();

        String phonenumber();

        String address();
    }
}
