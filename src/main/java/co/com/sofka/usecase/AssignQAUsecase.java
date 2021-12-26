package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.commands.AssignQACommand;
import co.com.sofka.domain.performance.Performance;

public class AssignQAUsecase extends UseCase<RequestCommand<AssignQACommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AssignQACommand> requestCommand) {
        var command = requestCommand.getCommand();
        var performance = Performance.from(command.getPerformanceId(), retrieveEvents());

        if(!(performance.qa()==null))
            throw new BusinessException(command.getPerformanceId().value(), "A QA already assigned, try with command 'ChangeQACommand'");

        performance.assignQA(command.getQaId(), command.getPersonalInformation(), command.getOrder());

        emit().onResponse(new ResponseEvents(performance.getUncommittedChanges()));
    }
}
