package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.bicycle.commands.CreateBicycleCommand;

public class CreateBicycleUsecase extends UseCase<RequestCommand<CreateBicycleCommand>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CreateBicycleCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var bicycle = new Bicycle(command.getBicycleId(),
                command.getClientId(),
                    command.getContactDetail());

        emit().onResponse(new ResponseEvents(bicycle.getUncommittedChanges()));
    }
}
