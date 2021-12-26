package co.com.sofka.domain.bicycle;

import co.com.sofka.domain.bicycle.values.BicycleCharacteristics;
import co.com.sofka.domain.bicycle.values.EndorsedBy;
import co.com.sofka.domain.bicycle.values.PropertyCardId;
import co.com.sofka.domain.generic.Entity;

import java.util.Objects;

public class PropertyCard extends Entity<PropertyCardId> {

    protected BicycleCharacteristics bicycleCharacteristics;
    protected EndorsedBy endorsedBy;

    public PropertyCard(PropertyCardId entityId, BicycleCharacteristics bicycleCharacteristics, EndorsedBy endorsedBy) {
        super(entityId);
        this.bicycleCharacteristics = bicycleCharacteristics;
        this.endorsedBy = endorsedBy;
    }

    public void changeOwner(String newOwnerName) {
        Objects.requireNonNull(newOwnerName, "newOwnerName cannot be null");
        bicycleCharacteristics = bicycleCharacteristics.changeOwner(newOwnerName);
    }

    public void changeColor(String newColor){
        Objects.requireNonNull(newColor, "newColor cannot be null");
        bicycleCharacteristics = bicycleCharacteristics.changeColor(newColor);
    }

    public String generatePropertyCard(){
      return "Owner";
    };

    public BicycleCharacteristics bicycleCharacteristics() {
        return bicycleCharacteristics;
    }

    public EndorsedBy endorsedBy() {
        return endorsedBy;
    }
}
