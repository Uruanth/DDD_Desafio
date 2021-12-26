package co.com.sofka.usecase;

import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.domain.commands.AssignSupervisorCommand;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.team.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssignSupervisorUsecaseTest {

    @Mock
    DomainEventRepository repository;
/*
    @Test
    void assignSuprvisor() {
        //Arrange
        TeamId teamId = TeamId.from("tttt");
        SupervisorId supervisorId = SupervisorId.from("ssss");
        ResponsibleArea responsibleArea = new ResponsibleArea(4, 0);
        PersonalInformation personalInformation = new PersonalInformation("Dairon",
                "31153564", "mechanic");

        var command = new AssignSupervisorCommand(teamId, supervisorId, personalInformation, responsibleArea);
        var usecase = new AssignSupervisorUsecase();

        when(repository.getEventsBy("tttt")).thenReturn(events());
        usecase.addRepository(repository);



    }
*/
}