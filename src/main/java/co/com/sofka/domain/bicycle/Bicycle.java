package co.com.sofka.domain.bicycle;

import co.com.sofka.domain.bicycle.values.*;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.events.*;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.performance.values.*;
import co.com.sofka.domain.team.values.TeamId;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Bicycle extends AggregateEvent<BicycleId> {

    protected PerformanceId performanceId;
    protected TeamId teamId;
    protected PropertyCard propertyCard;
    protected Machinary machinery;
    protected Client client;


    public Bicycle(BicycleId bicycleId, ClientId clientId,  ContactDetail contactDetail) {
        super(bicycleId);
        subscribe(new BicycleChange(this));
        appendChange(new BicycleCreated(clientId,  contactDetail)).apply();

    }

    private Bicycle(BicycleId bicycleId) {
        super(bicycleId);
        subscribe(new BicycleChange(this));
    }

    public static Bicycle from(BicycleId bicycleId, List<DomainEvent> events){
        var bicycle = new Bicycle(bicycleId);
        events.forEach(bicycle::applyEvent);
        return  bicycle;
    }

    public void assignClient(ClientId clientId, ContactDetail contactDetail){
        Objects.requireNonNull(clientId, "clientId cannot be null");
        Objects.requireNonNull(contactDetail, "contactDetail cannot be null");
        appendChange(new AssignedClient( clientId,  contactDetail)).apply();
    }

    public void assignPerformance(PerformanceId newPerformanceId){
        Objects.requireNonNull(newPerformanceId, "newPerformanceId cannot be null");
        appendChange(new AssignedPerformance(newPerformanceId)).apply();
    }

    public void changePerformance(PerformanceId newPerformanceId){
        Objects.requireNonNull(newPerformanceId, "newPerformanceId cannot be null");
        appendChange(new ChangedPerformance(newPerformanceId)).apply();
    }

    public void assignTeam(TeamId newTeamId){
        Objects.requireNonNull(newTeamId, "newTeamId cannot be null");
        appendChange(new AssignedTeam(newTeamId)).apply();
    }

    public void changeTeam(TeamId newTeamId){
        Objects.requireNonNull(newTeamId, "newTeamId cannot be null");
        appendChange(new ChangedTeam(newTeamId)).apply();
    }


    public void saveMachinary(MachineryId machineryId, BikeSpecifications bikeSpecifications){
        Objects.requireNonNull(machineryId, "machineryId cannot be null");
        Objects.requireNonNull(bikeSpecifications, "bikeSpecifications cannot be null");
        appendChange(new SavedMachinary(machineryId, bikeSpecifications)).apply();

    }

    public void updatePropertyCard(String nameOwner, String color){
        Objects.requireNonNull(nameOwner, "nameOwner cannot be null");
        Objects.requireNonNull(color, "color cannot be null");
        appendChange(new UpdatedPropertyCard(nameOwner, color)).apply();
    }

    public void cancelOrder(ClientId clientId){
        Objects.requireNonNull(clientId, "client cannot be null");
        appendChange(new CanceledOrder(clientId)).apply();

    }

    public void createOrder(ClientId clientId, PropertyCardId propertyCardId, ClientRequest specifications){
        Objects.requireNonNull(clientId, "client cannot be null");
        Objects.requireNonNull(propertyCardId, "propertyCardId cannot be null");
        Objects.requireNonNull(specifications, "specifications cannot be null");
        appendChange(new CreatedOrder(clientId, propertyCardId, specifications)).apply();
    }

    public void changeOrder(ClientId clientId, PropertyCardId propertyCardId, ClientRequest specifications, Order order){
        Objects.requireNonNull(clientId, "client cannot be null");
        Objects.requireNonNull(propertyCardId, "propertyCardId cannot be null");
        Objects.requireNonNull(specifications, "specifications cannot be null");
        appendChange(new ChangedOrder(clientId, propertyCardId, specifications, order)).apply();
    }

    public void createPropertyCard( BicycleCharacteristics bicycleCharacteristics, EndorsedBy endorsedBy){
        Objects.requireNonNull(bicycleCharacteristics, "bicycleCharacteristics cannot be null");
        Objects.requireNonNull(endorsedBy, "endorsedBy cannot be null");

        appendChange(new CreatedPropertyCard( this.identity(),  bicycleCharacteristics,  endorsedBy)).apply();
    }


    public PerformanceId performanceId() {
        return performanceId;
    }

    public TeamId teamId() {
        return teamId;
    }

    public PropertyCard propertyCard() {
        return propertyCard;
    }

    public Machinary machinery() {
        return machinery;
    }

    public Client client() {
        return client;
    }
}
