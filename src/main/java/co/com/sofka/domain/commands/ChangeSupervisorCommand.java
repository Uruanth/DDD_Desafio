package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.ResponsibleArea;
import co.com.sofka.domain.team.values.SupervisorId;
import co.com.sofka.domain.team.values.TeamId;

public class ChangeSupervisorCommand extends Command {
    private final ResponsibleArea responsibleArea;
    private final TeamId teamId;
    private final SupervisorId supervisorId;
    private final PersonalInformation personalInformation;

    public ResponsibleArea getResponsibleArea() {
        return responsibleArea;
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public SupervisorId getSupervisorId() {
        return supervisorId;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public ChangeSupervisorCommand(TeamId teamId, SupervisorId supervisorId, PersonalInformation personalInformation, ResponsibleArea responsibleArea) {
        this.teamId = teamId;
        this.supervisorId = supervisorId;
        this.personalInformation = personalInformation;
        this.responsibleArea = responsibleArea;
    }
}
