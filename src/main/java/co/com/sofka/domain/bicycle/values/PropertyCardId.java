package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.Identity;

public class PropertyCardId extends Identity {

    private PropertyCardId(String value) {
        super(value);
    }

    public PropertyCardId() {
    }

    public static PropertyCardId from(String value) {
        return new PropertyCardId(value);
    }
}
