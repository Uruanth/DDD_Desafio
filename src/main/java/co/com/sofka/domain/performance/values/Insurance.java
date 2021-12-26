package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Insurance implements ValueObject<String> {
    private final String name;

    public Insurance(String name) {
        if(name.isBlank()) throw new IllegalArgumentException("name cannot be empty");
        this.name = Objects.requireNonNull(name, "name cannot be null");
    }

    @Override
    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Insurance insurance = (Insurance) o;

        return name != null ? name.equals(insurance.name) : insurance.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
