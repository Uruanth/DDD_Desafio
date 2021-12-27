package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.commands.ChangeEngineerCommand;
import co.com.sofka.domain.performance.Performance;

public class ChangeEngineerUsecase extends UseCase<RequestCommand<ChangeEngineerCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ChangeEngineerCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var performance = Performance.from(command.getPerformanceId(), retrieveEvents());
        if(performance.engineer() == null)
            throw new BusinessException(command.getPerformanceId().value(),
                    "There is still no engineer assigned to performance, try with 'AssignEngineerComand' command");

        performance.changeEnginner(command.getEngineerId(),
                command.getPersonalInformation(),
                command.getCommissionedArea(),
                command.getOrder());

        emit().onResponse(new ResponseEvents(performance.getUncommittedChanges()));
    }
}
