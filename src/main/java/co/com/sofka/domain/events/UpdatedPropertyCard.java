package co.com.sofka.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class UpdatedPropertyCard extends DomainEvent {
    private final String nameOwner;
    private final String color;

    public String getNameOwner() {
        return nameOwner;
    }

    public String getColor() {
        return color;
    }

    public UpdatedPropertyCard(String nameOwner, String color) {
        super("sofka.bicycle.updatedPropertyCard");
        this.nameOwner = nameOwner;
        this.color = color;
    }
}
