package co.com.sofka.domain.team;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.events.*;

public class TeamChange extends EventChange {
    public TeamChange(Team team) {

        apply((CreatedTeam event) -> {
            team.supervisor = new Supervisor(
                    event.getSupervisorId(),
                    event.getPersonalInformation(),
                    event.getResponsibleArea()
            );


        });

        apply((AssignedMechanic event) -> {
            team.mechanic = new Mechanic(
                    event.getMechanicId(),
                    event.getBicycleType(),
                    event.getPersonalInformation(),
                    event.getTools()
            );
        });

        apply((AssignedSupervisor event) -> {
            team.supervisor = new Supervisor(
                    event.getSupervisorId(),
                    event.getDatosPersonales(),
                    event.getResponsibleArea()
            );
        });

        apply((AssignedPainter event) -> {
            team.painter = new Painter(
                    event.getPintorId(),
                    event.getPaintType(),
                    event.getDatosPersonales(),
                    event.getTechnique()
            );
        });

        apply((ChangedMechanic event) -> {
            team.mechanic = new Mechanic(
                    event.getMechanicId(),
                    event.getTipoBicicleta(),
                    event.getDatosPersonales(),
                    event.getHerramientas()
            );
        });

        apply((ChangedSupervisor event) -> {
            team.supervisor = new Supervisor(
                    event.getSupervisorId(),
                    event.getDatosPersonales(),
                    event.getResponsibleArea()
            );
        });

        apply((ChangedPainter event) -> {
            team.painter = new Painter(
                    event.getPintorId(),
                    event.getPaintType(),
                    event.getDatosPersonales(),
                    event.getTechnique()
            );
        });

    }
}
