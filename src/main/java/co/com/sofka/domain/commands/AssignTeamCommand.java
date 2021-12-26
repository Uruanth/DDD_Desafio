package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.team.values.TeamId;

public class AssignTeamCommand extends Command {

    private final BicycleId bicycleId;
    private final TeamId teamId;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public TeamId getTeamId() {
        return teamId;
    }

    public AssignTeamCommand(BicycleId bicycleId, TeamId teamId) {
        this.bicycleId = bicycleId;
        this.teamId = teamId;
    }
}
