package co.com.sofka.utils;

import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.events.*;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.PerformanceId;
import co.com.sofka.domain.performance.values.PilotId;
import co.com.sofka.domain.performance.values.QAId;
import co.com.sofka.domain.team.values.MechanicId;
import co.com.sofka.domain.team.values.PainterId;
import co.com.sofka.domain.team.values.SupervisorId;

public class Aggregates {

//-------------------------------------------------------------------------------------------------------------
//----------------------------------TEAM-EVENTS----------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------
    public static final DomainEvent NEW_TEAM = new CreatedTeam(
            SupervisorId.from("supervisor_static"),
            Values.PERSONAL_INFORMATION_SUPERVISOR,
            Values.RESPONSIBLE_AREA_SUPERVISOR
    );

    public static final DomainEvent ASSIGN_MECHANIC = new AssignedMechanic(
            MechanicId.from("MechanicStatic"),
            Values.BICYCLE_TYPE,
            Values.PERSONAL_INFORMATION_MECHANIC,
            Values.TOOLS
    );

    public static final DomainEvent ASSIGN_PAINTER = new AssignedPainter(
            PainterId.from("PainterStatic"),
            Values.PAINTER_TYPE,
            Values.PERSONAL_INFORMATION_PAINTER,
            Values.TECHNIQUE
    );

//-------------------------------------------------------------------------------------------------------------
//----------------------------------PERFORMACE-EVENTS----------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------

    public static final DomainEvent NEW_PERFORMANCE = new CreatedPerformance(
            PilotId.from("pilot_static"),
            Values.PERSONAL_INFORMATION_PILOT,
            Values.ORDER_1,
            Values.INSURANCE,
            Values.EMERGENCY_DATA
    );

    public static final DomainEvent ASSIGN_QA = new AssignedQA(
            QAId.from("QAStatic"),
            Values.PERSONAL_INFORMATION_QA,
            Values.ORDER_1
    );
    public static final DomainEvent ASSIGN_ENGINNER = new AssignedEnginner(
            EngineerId.from("EnginnerStatic"),
            Values.PERSONAL_INFORMATION_ENGINEER,
            Values.COMMISIONED_AREA,
            Values.ORDER_1
    );

//-------------------------------------------------------------------------------------------------------------
//----------------------------------BICYCLE-EVENTS-------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------


    public static final DomainEvent NEW_BICYCLE = new BicycleCreated(
            ClientId.from("client_static"),
            Values.CONTACT_DETAIL
    );

    public static final DomainEvent ASSIGN_PERFORMANCE = new AssignedPerformance(
            PerformanceId.from("TestPerformanceId")
    );

}
