package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.commands.ChangeEngineerCommand;
import co.com.sofka.domain.events.ChangedEnginner;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.CommissionedArea;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.PerformanceId;
import co.com.sofka.domain.performance.values.RequestPerformance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static co.com.sofka.utils.Aggregates.ASSIGN_ENGINNER;
import static co.com.sofka.utils.Aggregates.NEW_PERFORMANCE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChangeEngineerUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    //Arrange
    void  changedEngineer() {
        PerformanceId performanceId = PerformanceId.from("performanceTest");
        EngineerId engineerId = EngineerId.from("engineerTest");
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
        "31133254687", "ENGINEER");
        CommissionedArea commissionedArea = new CommissionedArea("Buga", Set.of());
        RequestPerformance order = new RequestPerformance(44D, 70D, 3D);

        var command = new ChangeEngineerCommand(performanceId,
                engineerId,personalInformation,
                commissionedArea,
                order);

        var usecase = new ChangeEngineerUsecase();

        Mockito.when(repository.getEventsBy("performanceTest")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(performanceId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        var event = (ChangedEnginner) events.getDomainEvents().get(0);

        //Assert
        Assertions.assertEquals(personalInformation, event.getDatosPersonales());
        Assertions.assertEquals(engineerId, event.getIngenieroId());
        Assertions.assertEquals(commissionedArea, event.getCommissionedArea());
        Assertions.assertEquals(order, event.getOrder());
        Mockito.verify(repository).getEventsBy("performanceTest");
    }


    @Test
        //Arrange
    void  changedEngineer_Unassigned() {
        PerformanceId performanceId = PerformanceId.from("performanceTest");
        EngineerId engineerId = EngineerId.from("engineerTest");
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31133254687", "ENGINEER");
        CommissionedArea commissionedArea = new CommissionedArea("Buga", Set.of());
        RequestPerformance order = new RequestPerformance(44D, 70D, 3D);

        var command = new ChangeEngineerCommand(performanceId,
                engineerId,personalInformation,
                commissionedArea,
                order);

        var usecase = new ChangeEngineerUsecase();

        Mockito.when(repository.getEventsBy("performanceTest")).thenReturn(eventsError());
        usecase.addRepository(repository);

        //Act
        var message = Assertions.assertThrows(BusinessException.class, ()->{
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(performanceId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();



        //Assert
        Assertions.assertEquals("There is still no engineer assigned to performance, try with 'AssignEngineerComand' command",
                message);
        Mockito.verify(repository).getEventsBy("performanceTest");
    }


    private List<DomainEvent> events() {
        return List.of(NEW_PERFORMANCE, ASSIGN_ENGINNER);
    }

    private List<DomainEvent> eventsError() {
        return List.of(NEW_PERFORMANCE);
    }

}