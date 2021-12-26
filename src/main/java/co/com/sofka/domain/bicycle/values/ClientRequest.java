package co.com.sofka.domain.bicycle.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class ClientRequest implements ValueObject<ClientRequest.Properties> {
    private final Double size;
    private final String color;
    private final String type;

    public ClientRequest(Double size, String color, String type) {
        if(size <25 || size > 60) throw new IllegalArgumentException("size out of range");
        if(isBlankProperty(color)) throw new IllegalArgumentException("color cannot be empty");
        if(isBlankProperty(type)) throw new IllegalArgumentException("type cannot be empty");

        this.size = Objects.requireNonNull(size, "size cannot be null");
        this.color = Objects.requireNonNull(color, "color cannot be null");
        this.type = Objects.requireNonNull(type, "type cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public Double size() {
                return size;
            }

            @Override
            public String color() {
                return color;
            }

            @Override
            public String type() {
                return type;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientRequest that = (ClientRequest) o;

        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = size != null ? size.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public interface Properties{
        Double size();
        String color();
        String type();
    }

    private boolean isBlankProperty(String property){
        return property.isBlank();
    }
}
