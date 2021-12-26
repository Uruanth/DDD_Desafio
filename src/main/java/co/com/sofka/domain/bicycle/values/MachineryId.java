package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.Identity;

public class MachineryId extends Identity {
    private MachineryId(String value) {
        super(value);
    }

    public MachineryId() {
    }
    public static MachineryId from(String value) {
        return new MachineryId(value);
    }
}
