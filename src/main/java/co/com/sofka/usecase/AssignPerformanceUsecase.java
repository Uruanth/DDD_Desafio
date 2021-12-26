package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.commands.AssignPerformanceCommand;

public class AssignPerformanceUsecase extends UseCase<RequestCommand<AssignPerformanceCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AssignPerformanceCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var bicycle = Bicycle.from(command.getBicycleId(), retrieveEvents());
        if(!(bicycle.performanceId()==null))
            throw new BusinessException(command.getBicycleId().value(), "A performance already assinged, try with command 'ChangePerformanceCommand'");
        bicycle.assignPerformance(command.getPerformanceId());
        emit().onResponse(new ResponseEvents(bicycle.getUncommittedChanges()));
    }
}
