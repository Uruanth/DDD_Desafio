package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.Identity;

public class SupervisorId extends Identity {
    private SupervisorId(String value) {
        super(value);
    }

    public SupervisorId() {
    }

    public static SupervisorId from(String value) {
        return new SupervisorId(value);
    }
}
