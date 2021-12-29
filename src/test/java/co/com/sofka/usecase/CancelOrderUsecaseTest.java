package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.Order;
import co.com.sofka.domain.bicycle.commands.CancelAnOrderCommand;
import co.com.sofka.domain.bicycle.events.CanceledOrder;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.utils.Values;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static co.com.sofka.utils.Aggregates.CREATE_ORDER_CLIENT;
import static co.com.sofka.utils.Aggregates.NEW_BICYCLE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CancelOrderUsecaseTest {

    @Mock
    DomainEventRepository repository;

    @Test
    void cancelOrder() {
        //Arrange
        var bicycleId = BicycleId.from("BicycleIdTest");
        Order order = Values.ORDER_CLIENT;
        var command = new CancelAnOrderCommand(bicycleId, order);
        var usecase = new CancelOrderUsecase();

        when(repository.getEventsBy("BicycleIdTest")).thenReturn(events());
        usecase.addRepository(repository);

        //Act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(bicycleId.value())
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();

        var event = (CanceledOrder) events.getDomainEvents().get(0);

        //Assert
        Assertions.assertEquals(Values.ORDER_CLIENT, event.getOrder());
        verify(repository).getEventsBy("BicycleIdTest");
    }

    private List<DomainEvent> events() {
        return List.of(NEW_BICYCLE, CREATE_ORDER_CLIENT);
    }
}