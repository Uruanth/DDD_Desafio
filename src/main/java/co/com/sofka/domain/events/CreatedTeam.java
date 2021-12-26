package co.com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.ResponsibleArea;
import co.com.sofka.domain.team.values.SupervisorId;
import co.com.sofka.domain.team.values.TeamId;

public class CreatedTeam extends DomainEvent {
    private final SupervisorId supervisorId;
    private final PersonalInformation personalInformation;
    private final ResponsibleArea responsibleArea;



    public SupervisorId getSupervisorId() {
        return supervisorId;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public ResponsibleArea getResponsibleArea() {
        return responsibleArea;
    }

    public CreatedTeam(SupervisorId supervisorId, PersonalInformation personalInformation, ResponsibleArea responsibleArea) {
        super("sofka.team.createdTeam");
        this.supervisorId = supervisorId;
        this.personalInformation = personalInformation;
        this.responsibleArea = responsibleArea;
    }
}
