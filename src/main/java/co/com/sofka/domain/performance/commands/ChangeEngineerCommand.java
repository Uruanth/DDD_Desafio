package co.com.sofka.domain.performance.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.Performance;
import co.com.sofka.domain.performance.values.CommissionedArea;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.PerformanceId;
import co.com.sofka.domain.performance.values.RequestPerformance;

public class ChangeEngineerCommand extends Command {

    private final PerformanceId performanceId;
    private final EngineerId engineerId;
    private final PersonalInformation personalInformation;
    private final CommissionedArea commissionedArea;
    private final RequestPerformance order;

    public PerformanceId getPerformanceId() {
        return performanceId;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public CommissionedArea getCommissionedArea() {
        return commissionedArea;
    }

    public RequestPerformance getOrder() {
        return order;
    }

    public EngineerId getEngineerId() {
        return engineerId;
    }

    public ChangeEngineerCommand(PerformanceId performanceId, EngineerId engineerId, PersonalInformation personalInformation, CommissionedArea commissionedArea, RequestPerformance order) {
        this.performanceId = performanceId;
        this.engineerId = engineerId;
        this.personalInformation = personalInformation;
        this.commissionedArea = commissionedArea;
        this.order = order;
    }
}
