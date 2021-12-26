package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.commands.AssignPainterCommand;
import co.com.sofka.domain.events.AssignedMechanic;
import co.com.sofka.domain.events.AssignedPainter;
import co.com.sofka.domain.events.CreatedTeam;
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

import static co.com.sofka.utils.Aggregates.ASSIGN_PAINTER;
import static co.com.sofka.utils.Aggregates.NEW_TEAM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignPainterUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void assignPainter() {
        //Arrange
        TeamId teamId = TeamId.from("tttt");
        PainterId painterId = PainterId.from("pppp");
        PaintType paintType = new PaintType("DRY");
        Technique technique = new Technique("BRUSH");
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31153564", "mechanic");
        var command = new AssignPainterCommand(teamId, painterId, paintType, personalInformation, technique);
        var usecase= new AssignPainterUsecase();

        when(repository.getEventsBy("tttt")).thenReturn(eventsTeam());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(teamId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //Assert
        var event = (AssignedPainter) events.getDomainEvents().get(0);
        Assertions.assertEquals(painterId, event.getPintorId());
        Mockito.verify(repository).getEventsBy("tttt");
    }


    @Test
    void assignPainterError() {
        //Arrange
        TeamId teamId = TeamId.from("tttt");
        PainterId painterId = PainterId.from("pppp");
        PaintType paintType = new PaintType("DRY");
        Technique technique = new Technique("BRUSH");
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31153564", "mechanic");
        var command = new AssignPainterCommand(teamId, painterId, paintType, personalInformation, technique);
        var usecase= new AssignPainterUsecase();

        when(repository.getEventsBy("tttt")).thenReturn(eventsTeamError());
        usecase.addRepository(repository);

        //Act
        var message = Assertions.assertThrows(BusinessException.class, ()->{
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(teamId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

        //Assert

        Assertions.assertEquals("A painter is already assigned, try with 'ChangePainterCommand'",
                message);
        Mockito.verify(repository).getEventsBy("tttt");
    }


    private List<DomainEvent> eventsTeam() {
        var events = new ArrayList<DomainEvent>();
        events.add(NEW_TEAM);
        return events;
    }

    private List<DomainEvent> eventsTeamError() {
        var events = eventsTeam();
        events.add(ASSIGN_PAINTER);
        return events;
    }
}