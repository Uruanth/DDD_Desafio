package co.com.sofka.domain.performance;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.CommissionedArea;
import co.com.sofka.domain.performance.values.DTOReference;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.RequestPerformance;

import java.util.Objects;
import java.util.Set;

//todo: refactor order by requestPerfomance
public class Engineer extends Entity<EngineerId> {
    protected PersonalInformation personalInformation;
    protected CommissionedArea commissionedArea;
    protected RequestPerformance order;


    public Engineer(EngineerId engineerId, PersonalInformation personalInformation, CommissionedArea commissionedArea, RequestPerformance order) {
        super(engineerId);
        this.personalInformation = personalInformation;
        this.commissionedArea = commissionedArea;
        this.order = order;
    }

    public void updatePersonalPhoneNumber(String phoneNumber) {
        Objects.requireNonNull(phoneNumber, "phoneNumber cannot be null");
        personalInformation = personalInformation.updatePhoneNumber(phoneNumber);
    }

    public boolean checkOrder(DTOReference consultRefence) {
        var checkResistance =
                this.order.value().resistance()
                        <= (consultRefence.value().value().resistance());


        var checkSpeed =
                this.order.value().speed()
                        >= (consultRefence.value().value().speed());

        var checkWeight =
                this.order.value().weight()
                        <= (consultRefence.value().value().weight());

        var check = checkResistance && checkSpeed && checkWeight;
        return check;
    }

    public void chageCommissionedArea(String location, Set<String> processes) {
        Objects.requireNonNull(location, "location cannot be null");
        Objects.requireNonNull(processes, "processes cannot be null");
        this.commissionedArea = new CommissionedArea(location, processes);
    }

    public void changeOrder(Double resistance, Double speed, Double weight) {
        Objects.requireNonNull(resistance, "resistance cannot be null");
        Objects.requireNonNull(speed, "speed cannot be null");
        Objects.requireNonNull(weight, "weight cannot be null");
        order = new RequestPerformance(resistance, speed, weight);
    }

    public PersonalInformation datosPersonales() {
        return personalInformation;
    }

    public CommissionedArea commissionedArea() {
        return commissionedArea;
    }

    public RequestPerformance order() {
        return order;
    }
}
