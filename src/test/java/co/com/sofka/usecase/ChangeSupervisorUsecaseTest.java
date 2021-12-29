package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.team.commands.ChangeSupervisorCommand;
import co.com.sofka.domain.team.events.AssignedSupervisor;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static co.com.sofka.utils.Aggregates.NEW_TEAM;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeSupervisorUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void assignSuprvisor() {
        //Arrange
        TeamId teamId = TeamId.from("tttt");
        SupervisorId supervisorId = SupervisorId.from("ssss");
        ResponsibleArea responsibleArea = new ResponsibleArea(4, 0);
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31153564", "mechanic");

        var command = new ChangeSupervisorCommand(teamId, supervisorId, personalInformation, responsibleArea);
        var usecase = new ChangeSupervisorUsecase();

        when(repository.getEventsBy("tttt")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(teamId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        var event = (AssignedSupervisor) events.getDomainEvents().get(0);

        //Assert
        Assertions.assertEquals(personalInformation, event.getDatosPersonales());
        Assertions.assertEquals(supervisorId, event.getSupervisorId());
        verify(repository).getEventsBy("tttt");


    }

    private List<DomainEvent> events() {
        return List.of(NEW_TEAM);
    }

}