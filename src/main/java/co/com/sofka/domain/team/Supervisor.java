package co.com.sofka.domain.team;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.ResponsibleArea;
import co.com.sofka.domain.team.values.SupervisorId;

import java.util.Objects;

public class Supervisor extends Entity<SupervisorId>{

    protected PersonalInformation personalInformation;
    protected ResponsibleArea responsibleArea;

    public Supervisor(SupervisorId supervisorId, PersonalInformation personalInformation, ResponsibleArea responsibleArea) {
        super(supervisorId);
        this.personalInformation = personalInformation;
        this.responsibleArea = responsibleArea;
    }

    public void changeResponsibleArea(Integer area, Integer staffAmount) {
        Objects.requireNonNull(area, "Area cannot be null");
        Objects.requireNonNull(staffAmount, "StaffAmount cannot be null");
        this.responsibleArea = new ResponsibleArea(area, staffAmount);
    }

    public void updatePersonalPhoneNumber(String newPersonalPhoneNumber) {
        Objects.requireNonNull(newPersonalPhoneNumber, "phonenumber can't be null");
        this.personalInformation = this.personalInformation.updatePhoneNumber(newPersonalPhoneNumber);
    }

    public void changeStaffAmount(Integer staffAmount) {
        Objects.requireNonNull(staffAmount, "StaffAmount cannot be null");
        this.responsibleArea = new ResponsibleArea(this.responsibleArea.value().area(), staffAmount);
    }

    public PersonalInformation datosPersonales() {
        return personalInformation;
    }

    public ResponsibleArea responsibleArea() {
        return responsibleArea;
    }
}
