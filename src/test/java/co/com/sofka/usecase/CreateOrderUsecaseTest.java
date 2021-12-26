package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.bicycle.Bicycle;
import co.com.sofka.domain.bicycle.values.*;
import co.com.sofka.domain.commands.CreateOrderCommand;
import co.com.sofka.domain.events.BicycleCreated;
import co.com.sofka.domain.events.CreatedOrder;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.utils.Aggregates.ASSIGN_PERFORMANCE;
import static co.com.sofka.utils.Aggregates.NEW_BICYCLE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUsecaseTest {

    @Mock
    DomainEventRepository repository;


    @Test
    void createOrder() {
        //Arrange
        BicycleId bicycleId = BicycleId.from("xxxx");
        ClientId clientId = ClientId.from("yyyy");
        PropertyCardId propertyCardId = PropertyCardId.from("pppp");
        ContactDetail contactDetail = new ContactDetail("Dairon", "3113294143", "carrera 10");
        ClientRequest clientRequest = new ClientRequest(48D, "AZUL", "FIXED");

        var command = new CreateOrderCommand(bicycleId, clientId, propertyCardId, contactDetail, clientRequest, false, false);
        var usecase = new CreateOrderUsecase();

        when(repository.getEventsBy("xxxx")).thenReturn(eventsMock());

        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(bicycleId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        //Assert

        var event = (CreatedOrder) events.getDomainEvents().get(0);
        Assertions.assertEquals(clientRequest, event.getSpecifications());
        Mockito.verify(repository).getEventsBy("xxxx");

    }

    @Test
    void colorOutOfStock(){
        //Arrange
        BicycleId bicycleId = BicycleId.from("xxxx");
        ClientId clientId = ClientId.from("yyyy");
        PropertyCardId propertyCardId = PropertyCardId.from("pppp");
        ContactDetail contactDetail = new ContactDetail("Dairon", "3113294143", "carrera 10");
        ClientRequest clientRequest = new ClientRequest(48D, "VERDE", "FIXED");

        var command = new CreateOrderCommand(bicycleId, clientId, propertyCardId, contactDetail, clientRequest, false, false);
        var usecase = new CreateOrderUsecase();

        when(repository.getEventsBy("xxxx")).thenReturn(eventsMock());

        usecase.addRepository(repository);


      var message =  Assertions.assertThrows(BusinessException.class, ()->{
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(bicycleId.value())
                    .syncExecutor(usecase, new RequestCommand<>(command))
                    .orElseThrow();
        }).getMessage();

      Assertions.assertEquals("Green color out of stock", message);


    }

    private List<DomainEvent> eventsMock() {
        var event = new ArrayList<DomainEvent>();
        event.add(NEW_BICYCLE);
        return event;
    }

}