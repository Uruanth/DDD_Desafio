package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.team.commands.AssignMechanicCommand;
import co.com.sofka.domain.team.events.AssignedMechanic;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static co.com.sofka.utils.Aggregates.ASSIGN_MECHANIC;
import static co.com.sofka.utils.Aggregates.NEW_TEAM;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignMechanicUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void assignMechanic() {

        //Arrange
        TeamId teamId = TeamId.from("tttt");
        MechanicId mechanicId = MechanicId.from("mmmm");
        BicycleType bicycleType = new BicycleType("16T", "CITY");
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31153564", "mechanic");
        var tools = Set.of(new Tool("screw", 3.5D), new Tool("sand", 100D));

        var command = new AssignMechanicCommand(teamId, mechanicId, bicycleType,
                personalInformation, tools);

        var usecase = new AssignMechanicUsecase();

        when(repository.getEventsBy("tttt")).thenReturn(eventTeam());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(teamId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //Assert
        var event = (AssignedMechanic) events.getDomainEvents().get(0);
        Assertions.assertEquals(mechanicId, event.getMechanicId());
        Assertions.assertEquals(tools, event.getTools());
        Mockito.verify(repository).getEventsBy("tttt");


    }

    @Test
    void assignMechanicError() {
        //Arrange
        TeamId teamId = TeamId.from("tttt");
        MechanicId mechanicId = MechanicId.from("mmmm");
        BicycleType bicycleType = new BicycleType("16T", "CITY");
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31153564", "mechanic");
        var tools = Set.of(new Tool("screw", 3.5D), new Tool("sand", 100D));

        var command = new AssignMechanicCommand(teamId, mechanicId, bicycleType,
                personalInformation, tools);

        var usecase = new AssignMechanicUsecase();

        when(repository.getEventsBy("tttt")).thenReturn(eventTeamError());
        usecase.addRepository(repository);

        //Act
        var message = Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(teamId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

        //Assert
        Assertions.assertEquals("A mechanic is already assigned, try with 'ChangeMechanicCommand'",
                message);
        Mockito.verify(repository).getEventsBy("tttt");
    }


    private List<DomainEvent> eventTeam() {
        var events = new ArrayList<DomainEvent>();
        events.add(NEW_TEAM);
        return events;
    }

    private List<DomainEvent> eventTeamError() {
        var events = eventTeam();
        events.add(ASSIGN_MECHANIC);
        return events;

    }

}