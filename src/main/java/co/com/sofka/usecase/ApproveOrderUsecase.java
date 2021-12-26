package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.commands.ApproveProductCommand;
import co.com.sofka.domain.performance.Performance;

public class ApproveOrderUsecase extends UseCase<RequestCommand<ApproveProductCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ApproveProductCommand> requestCommand) {

        var command = requestCommand.getCommand();
        var performance = Performance.from(command.getPerformanceId(), retrieveEvents());

        if(performance.engineer() == null)
            throw new BusinessException(command.getBicycleId().value(), "Unassigned engineer");

        if(performance.qa() == null)
            throw new BusinessException(command.getBicycleId().value(), "Unassigned QA");

        performance.approveOrder(command.getBicycleId(), command.getClientId(),
                performance.engineer().identity(), performance.qa().identity(), performance.pilot().identity());

        emit().onResponse(new ResponseEvents(performance.getUncommittedChanges()));

    }
}
