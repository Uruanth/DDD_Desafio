package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.team.values.TeamId;

public class AssignedTeam extends DomainEvent {
    private final TeamId newTeamId;

    public TeamId getNewTeamId() {
        return newTeamId;
    }

    public AssignedTeam(TeamId newTeamId) {
        super("sofka.bicycle.assignedTeam");
        this.newTeamId = newTeamId;
    }
}
