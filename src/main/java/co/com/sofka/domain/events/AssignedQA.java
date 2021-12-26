package co.com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.RequestPerformance;
import co.com.sofka.domain.performance.values.QAId;

public class AssignedQA extends DomainEvent {
    private final QAId qaId;
    private final PersonalInformation personalInformation;
    private final RequestPerformance order;

    public QAId getQaId() {
        return qaId;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public RequestPerformance getOrder() {
        return order;
    }

    public AssignedQA(QAId qaId, PersonalInformation personalInformation, RequestPerformance order) {
        super("sofka.rendimiento.assignedQA");
        this.qaId = qaId;
        this.personalInformation = personalInformation;
        this.order = order;
    }
}
