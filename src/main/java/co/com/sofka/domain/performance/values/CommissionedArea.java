package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;
import java.util.Set;

public class CommissionedArea implements ValueObject<CommissionedArea.Properties> {

    private final String location;
    private final Set<String> processes;

    public CommissionedArea(String location, Set<String> processes) {
        if(location.isBlank()) throw new IllegalArgumentException("location cannot be empty");
        processes.forEach(process -> {
            if(process.isBlank()) throw new IllegalArgumentException("process cannot be empty");
        });
        this.location = Objects.requireNonNull(location, "location cannot be null");
        this.processes =Objects.requireNonNull(processes, "processes cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String location() {
                return location;
            }

            @Override
            public Set<String> processes() {
                return processes;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommissionedArea that = (CommissionedArea) o;

        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return processes != null ? processes.equals(that.processes) : that.processes == null;
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.hashCode() : 0;
        result = 31 * result + (processes != null ? processes.hashCode() : 0);
        return result;
    }

    public interface Properties{
        String location();
        Set<String> processes();
    }
}
