package co.com.sofka.domain.performance.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.RequestPerformance;

public class ChangeQACommand extends Command {
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

    public ChangeQACommand(BicycleId bicycleId, PersonalInformation personalInformation, RequestPerformance order) {
        this.bicycleId = bicycleId;
        this.personalInformation = personalInformation;
        this.order = order;
    }
}
