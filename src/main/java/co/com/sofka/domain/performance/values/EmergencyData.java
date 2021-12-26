package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class EmergencyData implements ValueObject<EmergencyData.Properties> {

    private final String phoneNumber;
    private final String contactName;
    private final String relationship;

    public EmergencyData(String phoneNumber, String contactName, String relationship) {
        if(phoneNumber.isBlank()) throw new IllegalArgumentException("phoneNumber cannot be empty");
        if(contactName.isBlank()) throw new IllegalArgumentException("contactName cannot be empty");
        if( relationship.isBlank()) throw new IllegalArgumentException("relationship cannot be empty");

        this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber cannot be null");
        this.contactName = Objects.requireNonNull(contactName, "contactName cannot be null");
        this.relationship =Objects.requireNonNull(relationship, "relationship cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String phoneNumber() {
                return phoneNumber;
            }

            @Override
            public String contactName() {
                return contactName;
            }

            @Override
            public String relationship() {
                return relationship;
            }
        };
    }


    public interface Properties{
        String phoneNumber();
        String contactName();
        String relationship();
    }
}
