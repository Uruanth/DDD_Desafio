package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.ResponsibleArea;

public class ChangeSupervisorCommand extends Command {
    private final ResponsibleArea responsibleArea;
    private final BicycleId bicycleId;
    private final PersonalInformation personalInformation;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public ResponsibleArea getResponsibleArea() {
        return responsibleArea;
    }

    public ChangeSupervisorCommand(BicycleId bicycleId, PersonalInformation personalInformation, ResponsibleArea responsibleArea) {
        this.bicycleId = bicycleId;
        this.personalInformation = personalInformation;
        this.responsibleArea = responsibleArea;
    }
}
