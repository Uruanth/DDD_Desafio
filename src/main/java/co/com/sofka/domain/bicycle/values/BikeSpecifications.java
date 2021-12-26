package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class BikeSpecifications implements ValueObject<BikeSpecifications.Properties> {

    private final String structure;
    private final Double tires;
    private final String brakes;

    public BikeSpecifications(String structure, Double tires, String brakes) {
        if(structure.isBlank()) throw new IllegalArgumentException("structure cannot be empty");
        if (brakes.isBlank())throw new IllegalArgumentException("brakes cannot be empty");
        if(tires<20 || tires > 36) throw new IllegalArgumentException("tire size out of range");

        this.structure = Objects.requireNonNull(structure, "structure cannot be null");
        this.tires = Objects.requireNonNull(tires, "tires cannot be null");
        this.brakes = Objects.requireNonNull(brakes, "brakes cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String structure() {
                return structure;
            }

            @Override
            public Double tires() {
                return tires;
            }

            @Override
            public String brakes() {
                return brakes;
            }
        };
    }

    public interface Properties{
        String structure();
        Double tires();
        String brakes();
    }
}
