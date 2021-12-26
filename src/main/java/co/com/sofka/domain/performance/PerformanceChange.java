package co.com.sofka.domain.performance;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.events.*;

public class PerformanceChange extends EventChange {
    public PerformanceChange(Performance performance) {
        apply((CreatedPerformance event) -> {
                performance.pilot = new Pilot(
                        event.getPilotId(),
                        event.getPersonalInformation(),
                        event.getOrder(),
                        event.getInsurance(),
                        event.getEmergencyData()
                );
        });

        apply((AssignedEnginner event)->{
            performance.engineer= new Engineer(
                    event.getEngineerId(),
                    event.getPersonalInformation(),
                    event.getCommissionedArea(),
                    event.getOrder()
            );
        });


        apply((AssignedQA event)->{
            performance.qa = new QA(
                    event.getQaId(),
                    event.getDatosPersonales(),
                    event.getOrder()
            );
        });

        apply((ChangedEnginner event)->{
            performance.engineer= new Engineer(
                    event.getIngenieroId(),
                    event.getDatosPersonales(),
                    event.getCommissionedArea(),
                    event.getOrder()
            );
        });

        apply((ChangedPilot event)->{
            performance.pilot = new Pilot(
                    event.getPilotId(),
                    event.getDatosPersonales(),
                    event.getOrder(),
                    event.getInsurance(),
                    event.getEmergencyData()
            );
        });

        apply((ChangedQA event)->{
            performance.qa = new QA(
                    event.getQaId(),
                    event.getDatosPersonales(),
                    event.getOrder()
            );
        });

        apply((ApprovedProduct event)->{
            System.out.println("Apprved product, send email to: " + event.getClientId());
        });

        apply((UnapprovedProduct event)->{
            System.out.println("Unapproved product, send email to performance and team of bicycleid: " +event.getBicycleId());
        });
    }
}
