package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.bicycle.commands.CreatePropertyCardCommand;

public class CreatePropertyCardUsecase extends UseCase<RequestCommand<CreatePropertyCardCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreatePropertyCardCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var bicycle = Bicycle.from(command.getBicycleId(), retrieveEvents());

        bicycle.createPropertyCard(command.getBicycleCharacteristics(), command.getEndorsedBy());

        emit().onResponse(new ResponseEvents(bicycle.getUncommittedChanges()));
    }
}
