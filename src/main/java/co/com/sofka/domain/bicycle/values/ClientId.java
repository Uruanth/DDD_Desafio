package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.Identity;

public class ClientId extends Identity {
    private ClientId(String value) {
        super(value);
    }

    public ClientId() {
    }

    public static ClientId from(String value) {
        return new ClientId(value);
    }
}
