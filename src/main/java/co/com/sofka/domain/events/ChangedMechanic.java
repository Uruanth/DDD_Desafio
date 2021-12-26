package co.com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.BicycleType;
import co.com.sofka.domain.team.values.MechanicId;
import co.com.sofka.domain.team.values.Tool;

import java.util.Set;

public class ChangedMechanic extends DomainEvent {
    private final MechanicId mechanicId;
    private final BicycleType bicycleType;
    private final PersonalInformation personalInformation;
    private final Set<Tool> tools;

    public MechanicId getMechanicId() {
        return mechanicId;
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

    public ChangedMechanic(MechanicId mechanicId, BicycleType bicycleType, PersonalInformation personalInformation, Set<Tool> tools) {
        super("sofka.personal.ChangedMechanic");
        this.mechanicId = mechanicId;
        this.bicycleType = bicycleType;
        this.personalInformation = personalInformation;
        this.tools = tools;
    }
}
