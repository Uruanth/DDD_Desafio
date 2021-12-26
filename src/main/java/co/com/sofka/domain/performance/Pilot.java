package co.com.sofka.domain.performance;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.performance.values.*;
import co.com.sofka.domain.generics.PersonalInformation;

import java.util.Objects;

public class Pilot extends Entity<PilotId> {

    protected PersonalInformation personalInformation;
    protected RequestPerformance order;
    protected Insurance insurance;
    protected EmergencyData emergencyData;

    public Pilot(PilotId pilotId, PersonalInformation personalInformation, RequestPerformance order, Insurance insurance, EmergencyData emergencyData) {
        super(pilotId);
        this.personalInformation = personalInformation;
        this.order = order;
        this.insurance = insurance;
        this.emergencyData = emergencyData;
    }

    public void updatePersonalPhoneNumber(String phoneNumber) {
        Objects.requireNonNull(phoneNumber, "phoneNumber cannot be null");
        personalInformation = personalInformation.updatePhoneNumber(phoneNumber);
    }

    public void updateInsurance(String insuranceName) {
        Objects.requireNonNull(insurance, "insurance cannot be null");
        insurance = new Insurance(insuranceName);
    }

    public void changeEmergencyData(String phoneNumber, String contactName, String relationship) {
        Objects.requireNonNull(phoneNumber, "phoneNumber cannot be null");
        Objects.requireNonNull(contactName, "contactName cannot be null");
        Objects.requireNonNull(relationship, "relationship cannot be null");
        emergencyData = new EmergencyData(phoneNumber, contactName, relationship);
    }

    public void changeOrder(Double resistance, Double speed, Double weight) {
        Objects.requireNonNull(resistance, "resistance cannot be null");
        Objects.requireNonNull(speed, "speed cannot be null");
        Objects.requireNonNull(weight, "weight cannot be null");
        order = new RequestPerformance(resistance, speed, weight);
    }

    public boolean testOrder(DTOReference consultRefence) {
        return this.order.value().speed() >= consultRefence.value().value().speed();
    }


}
