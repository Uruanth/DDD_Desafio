package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.commands.AssignMechanicCommand;
import co.com.sofka.domain.team.Team;
import co.com.sofka.domain.team.values.Tool;

import java.util.Set;

public class AssignMechanicUsecase extends UseCase<RequestCommand<AssignMechanicCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AssignMechanicCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var team = Team.from(command.getTeamId(), retrieveEvents());

        if(!(team.mechanic()==null)){
            throw new BusinessException(command.getTeamId().value(), "A mechanic is already assigned, try with 'ChangeMechanicCommand'");
        }

        team.assignMechanic(command.getMechanicId(), command.getBicycleType(),
                command.getPersonalInformation(), command.getTools());

        emit().onResponse(new ResponseEvents(team.getUncommittedChanges()));
    }
}
