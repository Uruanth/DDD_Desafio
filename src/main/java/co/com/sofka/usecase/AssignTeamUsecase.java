package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.commands.AssignTeamCommand;

public class AssignTeamUsecase extends UseCase<RequestCommand<AssignTeamCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AssignTeamCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var bicycle = Bicycle.from(command.getBicycleId(), retrieveEvents());
        if(!(bicycle.teamId() == null))
            throw new BusinessException(command.getBicycleId().value(),
                    "A team already assigned, try with command 'ChangeTeamCommand'");

        bicycle.assignTeam(command.getTeamId());

        emit().onResponse(new ResponseEvents(bicycle.getUncommittedChanges()));
    }
}
