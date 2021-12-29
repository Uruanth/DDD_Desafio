package co.com.sofka.domain.team.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.PaintType;
import co.com.sofka.domain.team.values.PainterId;
import co.com.sofka.domain.team.values.Technique;

public class AssignedPainter extends DomainEvent {
    private final PainterId painterId;
    private final PaintType paintType;
    private final PersonalInformation personalInformation;
    private final Technique technique;

    public PainterId getPintorId() {
        return painterId;
    }

    public PaintType getPaintType() {
        return paintType;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public Technique getTechnique() {
        return technique;
    }

    public AssignedPainter(PainterId painterId, PaintType paintType, PersonalInformation personalInformation, Technique technique) {
        super("sofka.personal.AssignedPainter");
        this.painterId = painterId;
        this.paintType = paintType;
        this.personalInformation = personalInformation;
        this.technique = technique;
    }
}
