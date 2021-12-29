package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.bicycle.values.*;
import co.com.sofka.domain.bicycle.commands.CreatePropertyCardCommand;
import co.com.sofka.domain.bicycle.events.CreatedPropertyCard;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.utils.Aggregates.NEW_BICYCLE;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePropertyCardUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void createdPropertyCard(){
        //Arrange
        BicycleId bicycleId = BicycleId.from("bbbb");
        EndorsedBy endorsedBy = new EndorsedBy("Min. Bicicle", "0006547T");
        BicycleCharacteristics bicycleCharacteristics = new BicycleCharacteristics("VERDE", "48", "MTB", "Dairon");
        var command = new CreatePropertyCardCommand(bicycleId, bicycleCharacteristics, endorsedBy);
        var usecase = new CreatePropertyCardUsecase();

        when(repository.getEventsBy("bbbb")).thenReturn(eventsMock());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(bicycleId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();


        //Assert
        var event = (CreatedPropertyCard) events.getDomainEvents().get(0);
        Assertions.assertEquals(bicycleId, event.getIdentity());
        Assertions.assertEquals(bicycleCharacteristics, event.getBicycleCharacteristics());
        Assertions.assertEquals(endorsedBy, event.getEndorsedBy());
        Mockito.verify(repository).getEventsBy("bbbb");


    }

    private List<DomainEvent> eventsMock() {
        var events = new ArrayList<DomainEvent>();
        events.add(NEW_BICYCLE);
        return events;
    }


}