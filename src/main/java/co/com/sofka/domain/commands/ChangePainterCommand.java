package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.PaintType;
import co.com.sofka.domain.team.values.Technique;

public class ChangePainterCommand extends Command {
    private final BicycleId bicycleId;
    private final PaintType paintType;
    private final PersonalInformation personalInformation;
    private final Technique technique;


    public BicycleId getBicycleId() {
        return bicycleId;
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

    public ChangePainterCommand(BicycleId bicycleId, PaintType paintType, PersonalInformation personalInformation, Technique technique) {
        this.bicycleId = bicycleId;
        this.paintType = paintType;
        this.personalInformation = personalInformation;
        this.technique = technique;
    }

}
