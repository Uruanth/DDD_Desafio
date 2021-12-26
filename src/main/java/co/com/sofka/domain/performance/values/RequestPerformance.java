package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class RequestPerformance implements ValueObject<RequestPerformance.Properties> {

    private final Double resistance;
    private final Double speed;
    private final Double weight;

    public RequestPerformance(Double resistance, Double speed, Double weight) {
        if(speed<0) throw new IllegalArgumentException("speed out of range");
        if(weight<0) throw new IllegalArgumentException("weight out of range");

        this.resistance = Objects.requireNonNull(resistance, "resistance cannot be null");
        this.speed = Objects.requireNonNull(speed, "speed cannot be null");
        this.weight = Objects.requireNonNull(weight, "weight cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public Double resistance() {
                return resistance;
            }

            @Override
            public Double speed() {
                return speed;
            }

            @Override
            public Double weight() {
                return weight;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestPerformance requestPerformance = (RequestPerformance) o;

        if (resistance != null ? !resistance.equals(requestPerformance.resistance) : requestPerformance.resistance != null) return false;
        if (speed != null ? !speed.equals(requestPerformance.speed) : requestPerformance.speed != null) return false;
        return weight != null ? weight.equals(requestPerformance.weight) : requestPerformance.weight == null;
    }

    @Override
    public int hashCode() {
        int result = resistance != null ? resistance.hashCode() : 0;
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }

    public interface Properties{
        Double resistance();
        Double speed();
        Double weight();
    }
}
