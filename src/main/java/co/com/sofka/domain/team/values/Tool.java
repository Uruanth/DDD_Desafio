package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Tool implements ValueObject<Tool.Properties> {

    private final String function;
    private final Double size;

    public Tool(String function, Double size) {
        if(function.isBlank()) throw new IllegalArgumentException("function cannot be empty");
        if(size < 0) throw new IllegalArgumentException("size out of range");

        this.function = Objects.requireNonNull(function, "function cannot be null");
        this.size = Objects.requireNonNull(size, "size cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public String function() {
                return function;
            }

            @Override
            public Double size() {
                return size;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tool that = (Tool) o;

        if (function != null ? !function.equals(that.function) : that.function != null) return false;
        return size != null ? size.equals(that.size) : that.size == null;
    }

    @Override
    public int hashCode() {
        int result = function != null ? function.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }

   public interface Properties{
        String function();
        Double size();
    }

}
