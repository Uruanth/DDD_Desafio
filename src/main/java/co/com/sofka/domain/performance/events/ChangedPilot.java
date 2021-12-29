package co.com.sofka.domain.performance.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.EmergencyData;
import co.com.sofka.domain.performance.values.Insurance;
import co.com.sofka.domain.performance.values.RequestPerformance;
import co.com.sofka.domain.performance.values.PilotId;

public class ChangedPilot extends DomainEvent {
    private final PilotId pilotId;
    private final PersonalInformation personalInformation;
    private final RequestPerformance order;
    private final Insurance insurance;
    private final EmergencyData emergencyData;

    public PilotId getPilotId() {
        return pilotId;
    }

    public PersonalInformation getDatosPersonales() {
        return personalInformation;
    }

    public RequestPerformance getOrder() {
        return order;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public EmergencyData getEmergencyData() {
        return emergencyData;
    }

    public ChangedPilot(PilotId pilotId, PersonalInformation personalInformation, RequestPerformance order, Insurance insurance, EmergencyData emergencyData) {
        super("sofka.rendimiento.changedPilot");
        this.pilotId = pilotId;
        this.personalInformation = personalInformation;
        this.order = order;
        this.insurance = insurance;
        this.emergencyData = emergencyData;
    }
}
