package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.RequestPerformance;

public class AssignQACommand extends Command {

    private final BicycleId bicycleId;
    private final PersonalInformation personalInformation;
    private final RequestPerformance order;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public RequestPerformance getOrder() {
        return order;
    }

    public AssignQACommand(BicycleId bicycleId, PersonalInformation personalInformation, RequestPerformance order) {
        this.bicycleId = bicycleId;
        this.personalInformation = personalInformation;
        this.order = order;
    }
}
