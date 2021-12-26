package co.com.sofka.domain.performance;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.performance.values.DTOReference;
import co.com.sofka.domain.performance.values.RequestPerformance;
import co.com.sofka.domain.performance.values.QAId;
import co.com.sofka.domain.generics.PersonalInformation;

import java.util.Objects;

public class QA extends Entity<QAId>{

    protected PersonalInformation personalInformation;
    protected RequestPerformance order;

    public QA(QAId qaId, PersonalInformation personalInformation, RequestPerformance order) {
        super(qaId);
        this.personalInformation = personalInformation;
        this.order = order;
    }

    public void updatePersonalPhoneNumber(String phoneNumber) {
        Objects.requireNonNull(phoneNumber, "phoneNumber cannot be null");
        personalInformation = personalInformation.updatePhoneNumber(phoneNumber);
    }

    public boolean testOrder(DTOReference consultRefence){
        return this.order.value().resistance() <= consultRefence.value().value().resistance();
    }

    public void changeOrder(Double resistance, Double speed, Double weight){
        Objects.requireNonNull(resistance, "resistance cannot be null");
        Objects.requireNonNull(speed, "speed cannot be null");
        Objects.requireNonNull(weight, "weight cannot be null");
        order = new RequestPerformance(resistance, speed, weight);
    }
}
