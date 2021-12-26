package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class BicycleCharacteristics implements ValueObject<BicycleCharacteristics.Properties> {

    private final String color;
    private final String size;
    private final String type;
    private final String owner;

    public BicycleCharacteristics(String color, String size, String type, String owner) {

        if(isBlankProperty(color)) throw new IllegalArgumentException("color cannot be empty");
        if(isBlankProperty(size)) throw new IllegalArgumentException("size cannot be empty");
        if(isBlankProperty(type)) throw new IllegalArgumentException("type cannot be empty");
        if(isBlankProperty(owner)) throw new IllegalArgumentException("owner cannot be empty");

        this.color = Objects.requireNonNull(color, "color cannot be null");
        this.size = Objects.requireNonNull(size, "size cannot be null");
        this.type = Objects.requireNonNull(type, "type cannot be null");
        this.owner =Objects.requireNonNull(owner, "owner cannot be null");
    }

    public BicycleCharacteristics changeColor(String newColor) {
        return new BicycleCharacteristics(newColor, size, type, owner);
    }

    public BicycleCharacteristics changeOwner(String newOwner) {
        return new BicycleCharacteristics(color, size, type, newOwner);
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String color() {
                return color;
            }

            @Override
            public String size() {
                return size;
            }

            @Override
            public String type() {
                return type;
            }

            @Override
            public String owner() {
                return owner;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BicycleCharacteristics that = (BicycleCharacteristics) o;

        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    public interface Properties{
        String color();
        String size();
        String type();
        String owner();
    }

    private boolean isBlankProperty(String property){
        return property.isBlank();
    }

}
