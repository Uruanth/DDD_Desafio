package co.com.sofka.domain.bicycle;

import co.com.sofka.domain.bicycle.values.Order;
import co.com.sofka.domain.bicycle.values.PropertyCardId;
import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.events.*;
import co.com.sofka.domain.generic.Identity;

import java.util.stream.Collectors;

public class BicycleChange extends EventChange {
    public BicycleChange(Bicycle bicycle) {

        apply((BicycleCreated event) -> {
            bicycle.client = new Client(
                    event.getClientId(),
                    event.getContactDetail()
            );
        });

        apply((AssignedClient event) -> {
            bicycle.client = new Client(
                    event.getClientId(),
                    event.getContactDetail()
            );
        });

        apply((AssignedPerformance event) -> {
            bicycle.performanceId = event.getNewPerformanceId();
        });

        apply((ChangedPerformance event) -> {
            bicycle.performanceId = event.getNewPerformanceId();
        });

        apply((AssignedTeam event) -> {
            bicycle.teamId = event.getNewTeamId();
        });

        apply((ChangedTeam event) -> {
            bicycle.teamId = event.getNewTeamId();
        });


        apply((SavedMachinary event) -> {
            bicycle.machinery = new Machinary(event.getMachineryId(), event.getBikeSpecifications());
        });

        apply((UpdatedPropertyCard event) -> {
            bicycle.propertyCard.changeColor(event.getColor());
            bicycle.propertyCard.changeOwner(event.getNameOwner());
        });

        apply((CanceledOrder event) -> {
            bicycle.client.orders = bicycle.client.orders
                    .stream()
                    .map(orden -> {
                        if (orden.value().clientId().equals(event.getClientId()))
                            return new Order(
                                    orden.value().clientId(),
                                    orden.value().propertyCardId(),
                                    orden.value().specifications(),
                                    false);
                        else
                            return orden;
                    }).collect(Collectors.toSet());
        });

        apply((CreatedOrder event) -> {
            bicycle.client.addOrder(new Order(
                    event.getClientId(),
                    event.getPropertyCardId(),
                    event.getSpecifications(),
                    true
            ));

        });

        apply((ChangedOrder event) -> {
            bicycle.client.orders.remove(event.getOrder());
            bicycle.client.orders.add(new Order(
                    event.getClientId(),
                    event.getPropertyCardId(),
                    event.getSpecifications(),
                    true
            ));

        });

        apply((CreatedPropertyCard event)->{
            var propertyCardId = new Identity().generateUUID().toString();

            StringBuilder propertyCard = new StringBuilder();
            propertyCard.append("Client name:\t");
            propertyCard.append(event.getBicycleCharacteristics().value().owner());
            propertyCard.append("\n");
            propertyCard.append("Bicycle type:\t");
            propertyCard.append(event.getBicycleCharacteristics().value().type());
            propertyCard.append("\n");
            propertyCard.append("Color name:\t");
            propertyCard.append(event.getBicycleCharacteristics().value().owner());
            propertyCard.append("\n");
            propertyCard.append("Bike color:\t");
            propertyCard.append(event.getBicycleCharacteristics().value().color());
            propertyCard.append("\n");
            propertyCard.append("Propertycard #:\t");
            propertyCard.append(PropertyCardId.from(propertyCardId));
            propertyCard.append("\n");
            propertyCard.append("Endorsed by:\t");
            propertyCard.append(event.getEndorsedBy().value().name());
            propertyCard.append("\n");
            propertyCard.append("NIT:\t");
            propertyCard.append(event.getEndorsedBy().value().NIT());


            System.out.println(propertyCard);

            bicycle.propertyCard = new PropertyCard(PropertyCardId.from(propertyCardId),
                    event.getBicycleCharacteristics(),
                    event.getEndorsedBy());
        });
    }
}
