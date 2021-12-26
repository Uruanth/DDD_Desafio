package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.Identity;

public class PilotId extends Identity {
    private PilotId(String value) {
        super(value);
    }

    public PilotId() {
    }

    public static PilotId from(String value){
        return new PilotId(value);
    }
}
