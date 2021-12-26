package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.BicycleType;
import co.com.sofka.domain.team.values.Tool;

import java.util.Set;

public class ChangeMechanicCommand extends Command {
    private final BicycleId bicycleId;
    private final BicycleType bicycleType;
    private final PersonalInformation personalInformation;
    private final Set<Tool> tools;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public BicycleType getTipoBicicleta() {
        return bicycleType;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public Set<Tool> getHerramientas() {
        return tools;
    }

    public ChangeMechanicCommand(BicycleId bicycleId, BicycleType bicycleType, PersonalInformation personalInformation, Set<Tool> tools) {
        this.bicycleId = bicycleId;
        this.bicycleType = bicycleType;
        this.personalInformation = personalInformation;
        this.tools = tools;
    }
}
