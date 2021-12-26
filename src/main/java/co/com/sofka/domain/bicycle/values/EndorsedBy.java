package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class EndorsedBy implements ValueObject<EndorsedBy.Properties> {

    private final String name;
    private final String NIT;

    public EndorsedBy(String name, String NIT) {
        if(name.isBlank()) throw new IllegalArgumentException("name cannot be empty");
        if(NIT.isEmpty()) throw new IllegalArgumentException("NIT cannot be empty");

        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.NIT = Objects.requireNonNull(NIT, "NIT cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public String NIT() {
                return NIT;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndorsedBy)) return false;

        EndorsedBy that = (EndorsedBy) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return NIT != null ? NIT.equals(that.NIT) : that.NIT == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (NIT != null ? NIT.hashCode() : 0);
        return result;
    }

    public interface Properties{
        String name();
        String NIT();
    }
}
