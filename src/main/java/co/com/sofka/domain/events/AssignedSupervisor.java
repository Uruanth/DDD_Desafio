package co.com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.ResponsibleArea;
import co.com.sofka.domain.team.values.SupervisorId;

public class AssignedSupervisor extends DomainEvent {
    private final SupervisorId supervisorId;
    private final PersonalInformation personalInformation;
    private final ResponsibleArea responsibleArea;

    public SupervisorId getSupervisorId() {
        return supervisorId;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public ResponsibleArea getResponsibleArea() {
        return responsibleArea;
    }

    public AssignedSupervisor(SupervisorId supervisorId, PersonalInformation personalInformation, ResponsibleArea responsibleArea) {
        super("sofka.personal.AssignedSupervisor");
        this.supervisorId = supervisorId;
        this.personalInformation = personalInformation;
        this.responsibleArea = responsibleArea;
    }
}
