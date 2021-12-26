package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.Identity;

public class EngineerId extends Identity {

    private EngineerId(String value) {
        super(value);
    }

    public EngineerId() {
    }

    public static EngineerId from(String value) {
        return new EngineerId(value);
    }
}
