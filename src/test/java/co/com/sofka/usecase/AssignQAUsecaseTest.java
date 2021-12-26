package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.commands.AssignQACommand;
import co.com.sofka.domain.events.AssignedQA;
import co.com.sofka.domain.events.CreatedPerformance;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignQAUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void assignQA() {
        //Arrange

        PerformanceId performanceId = PerformanceId.from("pppp");
        RequestPerformance order = new RequestPerformance(45D, 60D, 4D);
        QAId qaId = QAId.from("qqqq");
        PersonalInformation personalInformation = new PersonalInformation("nameQA",
                "77874235", "calle apto 23");

        var command = new AssignQACommand(performanceId, qaId, personalInformation, order);
        var usecase = new AssignQAUsecase();

        when(repository.getEventsBy("pppp")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(performanceId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //Assert
        var event = (AssignedQA) events.getDomainEvents().get(0);
        Assertions.assertEquals(qaId, event.getQaId());
        Assertions.assertEquals(personalInformation, event.getDatosPersonales());
        Mockito.verify(repository).getEventsBy("pppp");
    }

    @Test
    void assignQA_AlreadyAssigned() {
        //Arrange
        PerformanceId performanceId = PerformanceId.from("pppp");
        RequestPerformance order = new RequestPerformance(45D, 60D, 4D);
        QAId qaId = QAId.from("qqqq");
        PersonalInformation personalInformation = new PersonalInformation("nameQA",
                "77874235", "calle apto 23");

        var command = new AssignQACommand(performanceId, qaId, personalInformation, order);
        var usecase = new AssignQAUsecase();

        when(repository.getEventsBy("pppp")).thenReturn(eventsError());
        usecase.addRepository(repository);

        //Act
        var message = Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(performanceId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

        //Assert

        Assertions.assertEquals("A QA already assigned, try with command 'ChangeQACommand'",
                message);

        Mockito.verify(repository).getEventsBy("pppp");
    }

    private List<DomainEvent> eventsError() {
        var events = events();
        RequestPerformance order = new RequestPerformance(45D, 60D, 4D);
        QAId qaId = QAId.from("testQA");
        PersonalInformation personalInformation = new PersonalInformation("nameQATest",
                "77996315", "otra casa Test");
        events.add(new AssignedQA(
                qaId, personalInformation, order
        ));
        return events;
    }


    private List<DomainEvent> events() {
        var events = new ArrayList<DomainEvent>();
        PersonalInformation personalInformation = new PersonalInformation("namePilot",
                "77874235", "calle apto 23");
        RequestPerformance order = new RequestPerformance(78D, 33D, 8D);
        Insurance insurance = new Insurance("Sura");
        EmergencyData emergencyData = new EmergencyData("77892225",
                "Martha", "Daugther");

        events.add(new CreatedPerformance(PilotId.from("pilotId"), personalInformation,
                order, insurance, emergencyData));

        return events;
    }

}