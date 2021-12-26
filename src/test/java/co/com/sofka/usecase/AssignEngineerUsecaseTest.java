package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.commands.AssignEngineerCommand;
import co.com.sofka.domain.events.AssignedEnginner;
import co.com.sofka.domain.events.CreatedPerformance;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static co.com.sofka.utils.Aggregates.ASSIGN_ENGINNER;
import static co.com.sofka.utils.Aggregates.NEW_PERFORMANCE;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignEngineerUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void assignEnginner() {

        PerformanceId performanceId = PerformanceId.from("pppp");
        EngineerId enginnerId = EngineerId.from("iiii");
        RequestPerformance order = new RequestPerformance(55D, 50D, 6D);
        PersonalInformation personalInformation = new PersonalInformation(
                "Dairon", "3666561154", "engineer1"
        );
        CommissionedArea commissionedArea = new CommissionedArea("Cali", Set.of("qa", "speed"));

        var command = new AssignEngineerCommand(performanceId, enginnerId,
                personalInformation, commissionedArea, order);
        var usecase = new AssignEngineerUsecase();

        when(repository.getEventsBy("pppp")).thenReturn(eventPerformance());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(performanceId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();


        //Assert
        var event = (AssignedEnginner) events.getDomainEvents().get(0);
        Assertions.assertEquals(enginnerId, event.getEngineerId());
        Assertions.assertEquals(order, event.getOrder());
        Assertions.assertEquals(personalInformation, event.getPersonalInformation());

    }
    @Test
    void assignEnginnerError() {

        PerformanceId performanceId = PerformanceId.from("pppp");
        EngineerId enginnerId = EngineerId.from("iiii");
        RequestPerformance order = new RequestPerformance(55D, 50D, 6D);
        PersonalInformation personalInformation = new PersonalInformation(
                "Dairon", "3666561154", "engineer1"
        );
        CommissionedArea commissionedArea = new CommissionedArea("Cali", Set.of("qa", "speed"));

        var command = new AssignEngineerCommand(performanceId, enginnerId,
                personalInformation, commissionedArea, order);
        var usecase = new AssignEngineerUsecase();

        when(repository.getEventsBy("pppp")).thenReturn(eventPerformanceError());
        usecase.addRepository(repository);

        //Act
        var message = Assertions.assertThrows(BusinessException.class,() ->{
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(performanceId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();


        //Assert
        Assertions.assertEquals("An engineer is already assigned, try with 'ChangeEngineerCommand'",
                message);
    }

    private List<DomainEvent> eventPerformance() {
        var events = new ArrayList<DomainEvent>();
        events.add(NEW_PERFORMANCE);
        return events;

    }

    private List<DomainEvent> eventPerformanceError() {
        var events = eventPerformance();
        events.add(ASSIGN_ENGINNER);
        return events;

    }




}