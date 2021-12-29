package co.com.sofka.domain.performance;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.events.*;
import co.com.sofka.domain.performance.values.*;

import java.util.List;
import java.util.Objects;

public class Performance extends AggregateEvent<PerformanceId> {

    protected Engineer engineer;
    protected Pilot pilot;
    protected QA qa;

    private Performance(PerformanceId performanceId) {
        super(performanceId);
        subscribe(new PerformanceChange(this));
    }

    public Performance(PerformanceId entityId, PilotId pilotId, PersonalInformation personalInformation, RequestPerformance order, Insurance insurance, EmergencyData emergencyData) {
        super(entityId);
        subscribe(new PerformanceChange(this));
        appendChange(new CreatedPerformance(pilotId, personalInformation, order, insurance, emergencyData)).apply();
    }

    public static Performance from(PerformanceId performanceId, List<DomainEvent> events) {
        var performance = new Performance(performanceId);
        events.forEach(performance::applyEvent);
        return performance;
    }

    public void assignEnginner(EngineerId engineerId, PersonalInformation personalInformation, CommissionedArea commissionedArea, RequestPerformance order) {
        Objects.requireNonNull(engineerId, "engineerId cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(commissionedArea, "commissionedArea cannot be null");
        Objects.requireNonNull(order, "order cannot be null");
        appendChange(new AssignedEnginner(engineerId, personalInformation, commissionedArea, order)).apply();
    }


    public void assignQA(QAId qaId, PersonalInformation personalInformation, RequestPerformance order) {
        Objects.requireNonNull(qaId, "qaId cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(order, "order cannot be null");
        appendChange(new AssignedQA(qaId, personalInformation, order)).apply();
    }

    public void changeEnginner(EngineerId engineerId, PersonalInformation personalInformation, CommissionedArea commissionedArea, RequestPerformance order) {
        Objects.requireNonNull(engineerId, "engineerId cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(commissionedArea, "commissionedArea cannot be null");
        Objects.requireNonNull(order, "order cannot be null");
        appendChange(new ChangedEnginner(engineerId, personalInformation, commissionedArea, order)).apply();
    }

    public void changePilot(PilotId pilotId, PersonalInformation personalInformation, RequestPerformance order, Insurance insurance, EmergencyData emergencyData) {
        Objects.requireNonNull(pilotId, "pilotId cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(order, "order cannot be null");
        Objects.requireNonNull(insurance, "insurance cannot be null");
        Objects.requireNonNull(emergencyData, "emergencyData cannot be null");
        appendChange(new ChangedPilot(pilotId, personalInformation, order, insurance, emergencyData)).apply();
    }

    public void changeQA(QAId qaId, PersonalInformation personalInformation, RequestPerformance order) {
        Objects.requireNonNull(qaId, "qaId cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(order, "order cannot be null");
        appendChange(new ChangedQA(qaId, personalInformation, order)).apply();
    }


    public void approveOrder(BicycleId bicycleId, ClientId clientId, EngineerId engineerId, QAId qaId, PilotId pilotId) {
        Objects.requireNonNull(bicycleId, "bicycleId cannot be null");
        Objects.requireNonNull(engineerId, "engineerId cannot be null");
        Objects.requireNonNull(qaId, "QAId cannot be null");
        Objects.requireNonNull(pilotId, "pilotId cannot be null");
        Objects.requireNonNull(clientId, "clientId cannot be null");
        var resultEngineer = engineer.checkOrder(consultRefence());
        var resultPilot = pilot.testOrder(consultRefence());
        var resultQA = qa.testOrder(consultRefence());

        if (resultEngineer && resultPilot && resultQA) {
            appendChange(new ApprovedProduct(bicycleId, clientId, engineerId, qaId, pilotId)).apply();
        } else {
            appendChange(new UnapprovedProduct(bicycleId, engineerId, qaId, pilotId)).apply();
        }
    }

    private DTOReference consultRefence() {
        return new DTOReference(
                new RequestPerformance(60D, 40D, 10D)
        );
    }

    public Engineer engineer() {
        return engineer;
    }

    public Pilot pilot() {
        return pilot;
    }

    public QA qa() {
        return qa;
    }
}
