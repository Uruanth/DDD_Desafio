package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class BicycleType implements ValueObject<BicycleType.Properties> {

    private final String pinion;
    private final String specialty;

    public BicycleType(String pinion, String specialty) {
        if(pinion.isBlank()) throw new IllegalArgumentException("pinion cannot be empty");
        if(specialty.isBlank()) throw new IllegalArgumentException("specialty cannot be empty");
        this.pinion = Objects.requireNonNull(pinion, "pinion cannot be null");
        this.specialty = Objects.requireNonNull(specialty, "specialty cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String pinhon() {
                return pinion;
            }

            @Override
            public String especialidad() {
                return specialty;
            }
        };
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BicycleType that = (BicycleType) o;

        if (pinion != null ? !pinion.equals(that.pinion) : that.pinion != null) return false;
        return specialty != null ? specialty.equals(that.specialty) : that.specialty == null;
    }

    @Override
    public int hashCode() {
        int result = pinion != null ? pinion.hashCode() : 0;
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        return result;
    }

    interface Properties{
        String pinhon();
        String especialidad();
    }

}
