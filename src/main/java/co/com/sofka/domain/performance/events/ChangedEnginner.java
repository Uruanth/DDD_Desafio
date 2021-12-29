package co.com.sofka.domain.performance.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.CommissionedArea;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.RequestPerformance;

public class ChangedEnginner extends DomainEvent {
    private final EngineerId engineerId;
    private final PersonalInformation personalInformation;
    private final CommissionedArea commissionedArea;
    private final RequestPerformance order;

    public EngineerId getIngenieroId() {
        return engineerId;
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

    public ChangedEnginner(EngineerId engineerId, PersonalInformation personalInformation, CommissionedArea commissionedArea, RequestPerformance order) {
        super("sofka.rendimiento.changedEnginner");
        this.engineerId = engineerId;
        this.personalInformation = personalInformation;
        this.commissionedArea = commissionedArea;
        this.order = order;
    }
}
