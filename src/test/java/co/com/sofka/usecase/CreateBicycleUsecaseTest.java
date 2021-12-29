package co.com.sofka.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.bicycle.values.ContactDetail;
import co.com.sofka.domain.bicycle.commands.CreateBicycleCommand;
import co.com.sofka.domain.bicycle.events.BicycleCreated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateBicycleUsecaseTest {

    @Test
    public void createBicycle(){

        //Arrange
        BicycleId bicycleId = BicycleId.from("xxxx");
        ClientId clientId = ClientId.from("yyyy");
        ContactDetail contactDetail = new ContactDetail("Dairon", "3113294143", "Garagoa-Boyaca");

        var command = new CreateBicycleCommand(bicycleId, clientId,  contactDetail);
        var usecase = new CreateBicycleUsecase();


        //Act
        var events = UseCaseHandler.getInstance()
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow();


        //Assert
        BicycleCreated event = (BicycleCreated) events.getDomainEvents().get(0);
        Assertions.assertEquals(event.aggregateRootId(), "xxxx");
        Assertions.assertEquals(event.getClientId().value(), "yyyy");

    }



}