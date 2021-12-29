package co.com.sofka.domain.team;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.team.events.*;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Team extends AggregateEvent<TeamId> {

    protected Mechanic mechanic;
    protected Painter painter;
    protected Supervisor supervisor;

    private Team(TeamId teamId) {
        super(teamId);
        subscribe(new TeamChange(this));
    }

/*
    public Team(TeamId teamId, MechanicId mechanicId, BicycleType bicycleType, PersonalInformation personalInformationMecanico, Set<Tool> tools,
                SupervisorId supervisorId, PersonalInformation personalInformationSupervisor, ResponsibleArea responsibleArea,
                PainterId pintorId, PaintType paintType, PersonalInformation personalInformationPintor, Technique technique) {
        super(teamId);
        Objects.requireNonNull(mechanicId, "mechanicId cannot be null");
        Objects.requireNonNull(bicycleType, "bicycleType cannot be null");
        Objects.requireNonNull(personalInformationMecanico, "personalInformationMecanico cannot be null");
        Objects.requireNonNull(tools, "tools cannot be null");
        Objects.requireNonNull(supervisorId, "supervisorId cannot be null");
        Objects.requireNonNull(personalInformationSupervisor, "personalInformationSupervisor cannot be null");
        Objects.requireNonNull(responsibleArea, "responsibleArea cannot be null");
        Objects.requireNonNull(pintorId, "pintorId cannot be null");
        Objects.requireNonNull(paintType, "paintType cannot be null");
        Objects.requireNonNull(personalInformationPintor, "personalInformationPintor cannot be null");
        Objects.requireNonNull(technique, "technique cannot be null");
        subscribe(new TeamChange(this));
        appendChange(new CreatedTeam(mechanicId, bicycleType, personalInformationMecanico, tools,
                 supervisorId, personalInformationSupervisor,  responsibleArea,
                 pintorId,  paintType, personalInformationPintor,  technique)).apply();
    }
*/

    public Team(TeamId entityId, SupervisorId supervisorId, PersonalInformation
            personalInformation, ResponsibleArea responsibleArea) {
        super(entityId);
        subscribe(new TeamChange(this));
        appendChange(new CreatedTeam( supervisorId,
                 personalInformation,  responsibleArea)).apply();
    }

    public static Team from(TeamId teamId, List<DomainEvent> events){
        var personal = new Team(teamId);
        events.forEach(personal::applyEvent);
        return personal;
    }

    public void assignMechanic(MechanicId mechanicId, BicycleType bicycleType, PersonalInformation personalInformation, Set<Tool> tools){
        Objects.requireNonNull(mechanicId, "mechanicId cannot be null");
        Objects.requireNonNull(bicycleType, "bicycleType cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(tools, "tools cannot be null");

        appendChange(new AssignedMechanic(mechanicId, bicycleType, personalInformation, tools)).apply();
    }

    public void assignSuprvisor(SupervisorId supervisorId, PersonalInformation personalInformation, ResponsibleArea responsibleArea){
        Objects.requireNonNull(supervisorId, "supervisorId cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(responsibleArea, "responsibleArea cannot be null");

        appendChange(new AssignedSupervisor(supervisorId, personalInformation, responsibleArea)).apply();
    }

    public void assignPainter(PainterId painterId, PaintType paintType, PersonalInformation personalInformation, Technique technique){
        Objects.requireNonNull(painterId, "painterId cannot be null");
        Objects.requireNonNull(paintType, "paintType cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(technique, "technique cannot be null");

        appendChange(new AssignedPainter(painterId, paintType, personalInformation, technique)).apply();
    }


    public void changeMechanic(MechanicId mechanicId, BicycleType bicycleType, PersonalInformation personalInformation, Set<Tool> tools){
        Objects.requireNonNull(mechanicId, "mechanicId cannot be null");
        Objects.requireNonNull(bicycleType, "bicycleType cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(tools, "tools cannot be null");

        appendChange(new ChangedMechanic(mechanicId, bicycleType, personalInformation, tools)).apply();
    }

    public void changeSuprvisor(SupervisorId supervisorId, PersonalInformation personalInformation, ResponsibleArea responsibleArea){
        Objects.requireNonNull(supervisorId, "supervisorId cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(responsibleArea, "responsibleArea cannot be null");

        appendChange(new ChangedSupervisor(supervisorId, personalInformation, responsibleArea)).apply();
    }

    public void changePainter(PainterId painterId, PaintType paintType, PersonalInformation personalInformation, Technique technique){
        Objects.requireNonNull(painterId, "painterId cannot be null");
        Objects.requireNonNull(paintType, "paintType cannot be null");
        Objects.requireNonNull(personalInformation, "personalInformation cannot be null");
        Objects.requireNonNull(technique, "technique cannot be null");

        appendChange(new ChangedPainter(painterId, paintType, personalInformation, technique)).apply() ;
    }


    public Mechanic mechanic() {
        return mechanic;
    }

    public Painter painter() {
        return painter;
    }

    public Supervisor supervisor() {
        return supervisor;
    }
}
