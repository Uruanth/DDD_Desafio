package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.commands.AssignSupervisorCommand;
import co.com.sofka.domain.team.Team;

public class AssignSupervisorUsecase extends UseCase<RequestCommand<AssignSupervisorCommand>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AssignSupervisorCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var team = Team.from(command.getTeamId(), retrieveEvents());

        if(!(team.supervisor()==null))
            throw new BusinessException(command.getTeamId().value(),"A supervisor already assigned, try with command 'ChangeSupervisorCommand'");

        team.assignSuprvisor(command.getSupervisorId(), command.getPersonalInformation(), command.getResponsibleArea());

        emit().onResponse(new ResponseEvents(team.getUncommittedChanges()));
    }
}
