package co.com.sofka.domain.team;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.team.values.PaintType;
import co.com.sofka.domain.team.values.PainterId;
import co.com.sofka.domain.team.values.Technique;
import co.com.sofka.domain.generics.PersonalInformation;

import java.util.Objects;

public class Painter extends Entity<PainterId> {

    protected PaintType paintType;
    protected PersonalInformation personalInformation;
    protected Technique technique;

    public Painter(PainterId painterId, PaintType paintType, PersonalInformation personalInformation, Technique technique) {
        super(painterId);
        this.paintType = paintType;
        this.personalInformation = personalInformation;
        this.technique = technique;
    }

    public Painter(PainterId entityId) {
        super(entityId);
    }

    public void changePaintType(String newPaintType) {
        Objects.requireNonNull(newPaintType, "newPaintType can't be null");
        this.paintType = new PaintType(newPaintType);
    }

    public void updatePersonalPhoneNumber(String newPersonalPhoneNumber) {
        Objects.requireNonNull(newPersonalPhoneNumber, "phonenumber can't be null");
        this.personalInformation = this.personalInformation.updatePhoneNumber(newPersonalPhoneNumber);
    }

    public void changeTechnique(String newTechnique) {
        Objects.requireNonNull(newTechnique, "newTechnique can't be null");
        this.technique = new Technique(newTechnique);
    }

    public PaintType paintType() {
        return paintType;
    }

    public PersonalInformation datosPersonales() {
        return personalInformation;
    }

    public Technique technique() {
        return technique;
    }
}
