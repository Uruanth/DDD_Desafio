package co.com.sofka.domain.performance.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.*;

public class ChangePilotCommand extends Command {
    private final PerformanceId performanceId;
    private final PilotId pilotId;
    private final PersonalInformation personalInformation;
    private final RequestPerformance order;
    private final Insurance insurance;
    private final EmergencyData emergencyData;

    public PerformanceId getPerformanceId() {
        return performanceId;
    }

    public PilotId getPilotId() {
        return pilotId;
    }

    public PersonalInformation getPersonalInformation() {
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

    public ChangePilotCommand(PerformanceId performanceId, PilotId pilotId, PersonalInformation personalInformation, RequestPerformance order, Insurance insurance, EmergencyData emergencyData) {
        this.performanceId = performanceId;
        this.pilotId = pilotId;
        this.personalInformation = personalInformation;
        this.order = order;
        this.insurance = insurance;
        this.emergencyData = emergencyData;
    }

}
