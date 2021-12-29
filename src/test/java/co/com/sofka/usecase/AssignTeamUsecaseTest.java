package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.commands.AssignTeamCommand;
import co.com.sofka.domain.bicycle.events.AssignedTeam;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.team.values.TeamId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static co.com.sofka.utils.Aggregates.ASSIGN_TEAM;
import static co.com.sofka.utils.Aggregates.NEW_BICYCLE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignTeamUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void assignTeam() {
        //Arrange
        var command = new AssignTeamCommand(BicycleId.from("bicycleTest"),
                TeamId.from("TeamTest"));
        var usecase = new AssignTeamUsecase();

        when(repository.getEventsBy("bicycleTest")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor("bicycleTest")
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        var event = (AssignedTeam) events.getDomainEvents().get(0);

        //Assert
        Assertions.assertEquals(TeamId.from("TeamTest"), event.getNewTeamId());
        verify(repository).getEventsBy("bicycleTest");

    }

    @Test
    void teamAlreadyAssigned() {
        //Arrange
        var command = new AssignTeamCommand(BicycleId.from("bicycleTest"),
                TeamId.from("TeamTest"));
        var usecase = new AssignTeamUsecase();

        when(repository.getEventsBy("bicycleTest")).thenReturn(eventsError());
        usecase.addRepository(repository);

        //Act
        var message = Assertions.assertThrows(BusinessException.class, ()->{
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor("bicycleTest")
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();


        //Assert
        Assertions.assertEquals("A team already assigned, try with command 'ChangeTeamCommand'", message);
        verify(repository).getEventsBy("bicycleTest");

    }

    private List<DomainEvent> eventsError() {
        return List.of(NEW_BICYCLE, ASSIGN_TEAM);
    }

    private List<DomainEvent> events() {
        return List.of(NEW_BICYCLE);
    }

}