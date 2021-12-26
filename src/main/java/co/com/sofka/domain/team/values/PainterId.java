package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.Identity;

public class PainterId extends Identity {
    private PainterId(String value) {
        super(value);
    }

    public PainterId() {
    }

    public static PainterId from(String value) {
        return new PainterId(value);
    }
}
