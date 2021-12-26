package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.Identity;

public class MechanicId extends Identity {

    public MechanicId() {
    }

    private MechanicId(String value) {
        super(value);
    }

    public static MechanicId from(String value){
        return new MechanicId(value);
    }

}
