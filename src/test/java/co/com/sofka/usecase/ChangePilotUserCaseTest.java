package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.commands.ChangePilotCommand;
import co.com.sofka.domain.events.ChangedPilot;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangePilotUserCaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void changePilot() {
        //Arrange
        PerformanceId performanceId = PerformanceId.from("pppp");
        PilotId pilotId = PilotId.from("iippii");
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31153564", "pilot");
        EmergencyData emergencyData = new EmergencyData("444655623", "Nora",
                "Wife");
        Insurance insurance = new Insurance("Sura");
        RequestPerformance order = new RequestPerformance(65D, 55D, 4D);

        var command = new ChangePilotCommand(
                performanceId, pilotId, personalInformation,
                order, insurance, emergencyData
        );
        var usecase = new ChangePilotUserCase();

        when(repository.getEventsBy("pppp")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(performanceId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //Assert
        var event = (ChangedPilot) events.getDomainEvents().get(0);
        Assertions.assertEquals(pilotId, event.getPilotId());
        Mockito.verify(repository).getEventsBy("pppp");

    }

    private List<DomainEvent> events() {
        var events = new ArrayList<DomainEvent>();
        PersonalInformation personalInformation = new PersonalInformation("namePilot", "77874235", "calle apto 23");
        RequestPerformance order = new RequestPerformance(78D, 33D,8D);
        Insurance insurance = new Insurance("Sura");
        EmergencyData emergencyData = new EmergencyData("77892225", "Martha", "Daugther");

        events.add(new CreatedPerformance(PilotId.from("pilotId"), personalInformation, order, insurance, emergencyData));

        return events;
    }

}