package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.bicycle.commands.CreateOrderCommand;

public class CreateOrderUsecase extends UseCase<RequestCommand<CreateOrderCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateOrderCommand> requestCommand) {
        var command = requestCommand.getCommand();

        var bicycle = Bicycle.from(command.getBicycleId(), retrieveEvents());
        if (command.getClientRequest().value().color().equals("VERDE")) {
            throw new BusinessException(command.getBicycleId().value(), "Green color out of stock");
        }

        bicycle.createOrder(command.getClienteId(), command.getPropertyCardId(), command.getClientRequest());
        emit().onResponse(new ResponseEvents(bicycle.getUncommittedChanges()));

    }
}
