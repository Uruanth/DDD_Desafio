package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.MechanicId;
import co.com.sofka.domain.team.values.TeamId;
import co.com.sofka.domain.team.values.Tool;
import co.com.sofka.domain.team.values.BicycleType;

import java.util.Set;

public class AssignMechanicCommand extends Command {

    private final TeamId teamId;
    private final MechanicId mechanicId;
    private final BicycleType bicycleType;
    private final PersonalInformation personalInformation;
    private final Set<Tool> tools;


    public TeamId getTeamId() {
        return teamId;
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public Set<Tool> getTools() {
        return tools;
    }

    public MechanicId getMechanicId() {
        return mechanicId;
    }

    public AssignMechanicCommand(TeamId teamId, MechanicId mechanicId, BicycleType bicycleType, PersonalInformation personalInformation, Set<Tool> tools) {
        this.teamId = teamId;
        this.mechanicId = mechanicId;
        this.bicycleType = bicycleType;
        this.personalInformation = personalInformation;
        this.tools = tools;
    }
}
