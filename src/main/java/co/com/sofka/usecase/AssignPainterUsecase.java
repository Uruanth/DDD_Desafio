package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.team.commands.AssignPainterCommand;
import co.com.sofka.domain.team.Team;

public class AssignPainterUsecase extends UseCase<RequestCommand<AssignPainterCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AssignPainterCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var team = Team.from(command.getTeamId(), retrieveEvents());

        if(!(team.painter()==null)){
            throw new BusinessException(command.getTeamId().value(), "A painter is already assigned, try with 'ChangePainterCommand'");
        }

        team.assignPainter(command.getPainterId(), command.getPaintType(),
                command.getPersonalInformation(), command.getTechnique());

        emit().onResponse(new ResponseEvents(team.getUncommittedChanges()));
    }
}
