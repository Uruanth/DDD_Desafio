package co.com.sofka.domain.bicycle.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.team.values.TeamId;

public class ChangedTeam extends DomainEvent {
    private final TeamId newTeamId;

    public TeamId getNewTeamId() {
        return newTeamId;
    }

    public ChangedTeam(TeamId newTeamId) {
        super("sofka.bicycle.changedTeam");
        this.newTeamId = newTeamId;
    }
}
