package co.com.sofka.utils;

import co.com.sofka.domain.bicycle.values.ContactDetail;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.CommissionedArea;
import co.com.sofka.domain.performance.values.EmergencyData;
import co.com.sofka.domain.performance.values.Insurance;
import co.com.sofka.domain.performance.values.RequestPerformance;
import co.com.sofka.domain.team.values.*;

import java.util.Set;

public class Values {

    public static final PersonalInformation PERSONAL_INFORMATION_SUPERVISOR = new PersonalInformation(
            "NameSupervisorStatic", "phoneSupervisorStatic", "SUPERVISOR"
    );

    public static final ResponsibleArea RESPONSIBLE_AREA_SUPERVISOR = new ResponsibleArea(
            4, 0
    );

    public static final PersonalInformation PERSONAL_INFORMATION_PILOT = new PersonalInformation(
            "NamePilotStatic", "phonePilotStatic", "PILOT"
    );
    public static final RequestPerformance ORDER_1 = new RequestPerformance(
            45D, 65D, 4D
    );

    public static final Insurance INSURANCE = new Insurance("SURA");

    public static final EmergencyData EMERGENCY_DATA = new EmergencyData(
            "phoneEmergencyData", "Batman", "Son"
    );

    public static final ContactDetail CONTACT_DETAIL = new ContactDetail(
            "Martha", "numberClientStatic", "AddresStatic"
    );


    public static final PersonalInformation PERSONAL_INFORMATION_QA = new PersonalInformation(
            "NameQAStatic", "phoneQAStatic", "QA"
    );
    public static final PersonalInformation PERSONAL_INFORMATION_ENGINEER = new PersonalInformation(
            "NameEngineerStatic", "phoneEngineerStatic", "ENGINEER"
    );
    public static final CommissionedArea COMMISIONED_AREA = new CommissionedArea(
            "Bogota", Set.of("RESISTENCE", "SPEED", "WEIGHT")
    );
    public static final BicycleType BICYCLE_TYPE = new BicycleType("FIXED", "CITY");
    public static final PersonalInformation PERSONAL_INFORMATION_MECHANIC = new PersonalInformation(
            "NameMechanicStatic", "phoneMechanicStatic", "MECHANIC"
    );
    public static final Set<Tool> TOOLS = Set.of(
            new Tool("HAMMER", 8D),
            new Tool("UNSCREW", 1.2D),
            new Tool("SANDING", 800D)
    );
    public static final PaintType PAINTER_TYPE = new PaintType("DRY");
    public static final PersonalInformation PERSONAL_INFORMATION_PAINTER = new PersonalInformation(
            "NamePAINTERStatic", "phonePAINTERStatic", "PAINTER"
    );
    public static final Technique TECHNIQUE = new Technique("BRUSH");
}
