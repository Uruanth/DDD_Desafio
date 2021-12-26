package co.com.sofka.domain.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.CommissionedArea;
import co.com.sofka.domain.performance.values.RequestPerformance;

public class ChangeEngineerCommand extends Command {

    private final BicycleId bicycleId;
    private final PersonalInformation personalInformation;
    private final CommissionedArea commissionedArea;
    private final RequestPerformance order;

    public BicycleId getBicycleId() {
        return bicycleId;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public CommissionedArea getCommissionedArea() {
        return commissionedArea;
    }

    public RequestPerformance getOrder() {
        return order;
    }

    public ChangeEngineerCommand(BicycleId bicycleId, PersonalInformation personalInformation, CommissionedArea commissionedArea, RequestPerformance order) {
        this.bicycleId = bicycleId;
        this.personalInformation = personalInformation;
        this.commissionedArea = commissionedArea;
        this.order = order;
    }
}
