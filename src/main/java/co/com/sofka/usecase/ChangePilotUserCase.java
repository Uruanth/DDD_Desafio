package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.performance.commands.ChangePilotCommand;
import co.com.sofka.domain.performance.Performance;

public class ChangePilotUserCase extends UseCase<RequestCommand<ChangePilotCommand>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<ChangePilotCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var performance = Performance.from(command.getPerformanceId(), retrieveEvents());
        performance.changePilot(command.getPilotId(), command.getPersonalInformation(),
                command.getOrder(), command.getInsurance(), command.getEmergencyData());

        emit().onResponse(new ResponseEvents(performance.getUncommittedChanges()));
    }
}
