package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.Identity;

public class BicycleId extends Identity {
    private BicycleId(String value) {
        super(value);
    }

    public BicycleId() {
    }

    public static BicycleId from(String value) {
        return new BicycleId(value);
    }
}
