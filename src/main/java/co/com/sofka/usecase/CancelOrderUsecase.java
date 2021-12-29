package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.bicycle.commands.CancelAnOrderCommand;

public class CancelOrderUsecase extends UseCase<RequestCommand<CancelAnOrderCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CancelAnOrderCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var bicycle = Bicycle.from(command.getBicycleId(), retrieveEvents());
        bicycle.cancelOrder(command.getOrder());

        emit().onResponse(new ResponseEvents(bicycle.getUncommittedChanges()));

    }
}
