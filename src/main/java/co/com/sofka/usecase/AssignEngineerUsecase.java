package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.commands.AssignEngineerCommand;
import co.com.sofka.domain.performance.Performance;
import co.com.sofka.domain.performance.values.PerformanceId;

public class AssignEngineerUsecase extends UseCase<RequestCommand<AssignEngineerCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AssignEngineerCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var performance = Performance.from(PerformanceId.from("pppp"), retrieveEvents());

        if(!(performance.engineer() ==  null)){
            throw new BusinessException(command.getPerformanceId().value(), "An engineer is already assigned, try with 'ChangeEngineerCommand'");
        }

        performance.assignEnginner(command.getEngineerId(), command.getDatosPersonales(),
                command.getCommissionedArea(), command.getOrder());

        emit().onResponse(new ResponseEvents(performance.getUncommittedChanges()));
    }
}
