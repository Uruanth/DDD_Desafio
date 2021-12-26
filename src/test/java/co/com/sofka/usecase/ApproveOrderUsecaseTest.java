package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.commands.ApproveProductCommand;
import co.com.sofka.domain.events.*;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.*;
import co.com.sofka.utils.Aggregates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static co.com.sofka.utils.Aggregates.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApproveOrderUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void approveOrder() {

        //Arrange
        BicycleId bicycleId = BicycleId.from("bbbb");
        ClientId clientId = ClientId.from("cccc");
        PerformanceId performanceId = PerformanceId.from("pppp");

        var command = new ApproveProductCommand(bicycleId, clientId, performanceId);
        var usecase = new ApproveOrderUsecase();

        when(repository.getEventsBy("pppp")).thenReturn(eventsMock());
        usecase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(performanceId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();


        var event = (ApprovedProduct) events.getDomainEvents().get(0);
        Assertions.assertEquals(clientId, event.getClientId());
        Assertions.assertEquals(EngineerId.from("EnginnerStatic"), event.getEnginnerId());

    }

    @Test
    void unapprovedProduct(){

        //Arrange
        BicycleId bicycleId = BicycleId.from("bbbb");
        ClientId clientId = ClientId.from("cccc");
        PerformanceId performanceId = PerformanceId.from("pppp");

        var command = new ApproveProductCommand(bicycleId, clientId, performanceId);
        var usecase = new ApproveOrderUsecase();

        when(repository.getEventsBy("pppp")).thenReturn(eventsMockUnapproved());
        usecase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(performanceId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();


        var event = (UnapprovedProduct) events.getDomainEvents().get(0);
        Assertions.assertEquals( EngineerId.from("engineer"), event.getEnginnerId());

    }

    @Test
    void unassginedQA(){
        //Arrange
        BicycleId bicycleId = BicycleId.from("bbbb");
        ClientId clientId = ClientId.from("cccc");
        PerformanceId performanceId = PerformanceId.from("pppp");

        var command = new ApproveProductCommand(bicycleId, clientId, performanceId);
        var usecase = new ApproveOrderUsecase();

        when(repository.getEventsBy("pppp")).thenReturn(eventsUnassignedQA());
        usecase.addRepository(repository);


        var message = Assertions.assertThrows(BusinessException.class, ()->{
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(performanceId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

        Assertions.assertEquals("Unassigned QA", message);
    }

    private List<DomainEvent> eventsMock() {

        var events = new ArrayList<DomainEvent>();
        events.add(NEW_PERFORMANCE);
        events.add(ASSIGN_ENGINNER);
        events.add(ASSIGN_QA);
        return events;
    }
/*
    private List<DomainEvent> eventsMockUnapproved() {
        var personalInformationPilot = new PersonalInformation("Piloto1",
                "311325564", "pilot");

        var personalInformationEnginner = new PersonalInformation("Engineer1",
                "99874662", "engineer");

        var personalInformationQA = new PersonalInformation("QA1",
                "33654721", "qa");

        var emergencyDataPilot = new EmergencyData("998224122",
                "Juan",
                "Brother"
        );

        var order = new RequestPerformance(70D, 40D, 12D);
        var events = new ArrayList<DomainEvent>();

        events.add(new CreatedPerformance(
                PilotId.from("pilot"),
                personalInformationPilot,
                order,
                new Insurance("SURA"),
                emergencyDataPilot));

        events.add(new AssignedEnginner(
                EngineerId.from("engineer"),
                personalInformationEnginner,
                new CommissionedArea("bogota", Set.of("vigilar", "probar")),
                order
        ));

        events.add(new AssignedQA(
                QAId.from("qaId"),
                personalInformationQA,
                order
        ));

        return events;
    }
*/
    private List<DomainEvent> eventsMockUnapproved() {
        var personalInformationEnginner = new PersonalInformation("Engineer1",
                "99874662", "engineer");
        var order = new RequestPerformance(70D, 40D, 12D);
        var events = new ArrayList<DomainEvent>();

        events.add(NEW_PERFORMANCE);
        events.add(ASSIGN_QA);
        events.add(new AssignedEnginner(
                EngineerId.from("engineer"),
                personalInformationEnginner,
                new CommissionedArea("bogota", Set.of("vigilar", "probar")),
                order
        ));

        return events;
    }
    private List<DomainEvent> eventsUnassignedQA() {
        var events = new ArrayList<DomainEvent>();
        events.add(NEW_PERFORMANCE);
        events.add(ASSIGN_ENGINNER);
         return events;
    }

}