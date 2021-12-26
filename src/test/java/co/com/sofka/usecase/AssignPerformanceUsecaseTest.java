package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ContactDetail;
import co.com.sofka.domain.commands.AssignPerformanceCommand;
import co.com.sofka.domain.events.AssignedPerformance;
import co.com.sofka.domain.events.BicycleCreated;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.performance.values.PerformanceId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignPerformanceUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void assignPerformance() {
        //Arrange
        var command = new AssignPerformanceCommand(BicycleId.from("bbbb"),
                PerformanceId.from("pppp"));

        var usecase = new AssignPerformanceUsecase();

        when(repository.getEventsBy("bbbb")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor("bbbb")
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //Assert
        var event = (AssignedPerformance) events.getDomainEvents().get(0);
        Assertions.assertEquals("pppp", event.getNewPerformanceId().value());
        Mockito.verify(repository).getEventsBy("bbbb");
    }

    @Test
    void assignPerformanceError() {
        //Arrange
        var command = new AssignPerformanceCommand(BicycleId.from("bbbb"),
                PerformanceId.from("pppp"));

        var usecase = new AssignPerformanceUsecase();

        when(repository.getEventsBy("bbbb")).thenReturn(eventsError());
        usecase.addRepository(repository);

        //Act
        var message = Assertions.assertThrows(BusinessException.class, ()->{
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor("bbbb")
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

        //Assert
        Assertions.assertEquals("A performance already assinged, try with command 'ChangePerformanceCommand'",
                message);
        Mockito.verify(repository).getEventsBy("bbbb");
    }

    private List<DomainEvent> eventsError() {
        var event = events();
        event.add(new AssignedPerformance(PerformanceId.from("TestPerformanceId")));
        return event;
    }

    private List<DomainEvent> events() {
        ClientId clientId = ClientId.from("yyyy");
        ContactDetail contactDetail = new ContactDetail("Dairon", "3113294143", "carrera 10");
        var events = new ArrayList<DomainEvent>();
        events.add(new BicycleCreated(clientId, contactDetail));
        return events;

    }

}