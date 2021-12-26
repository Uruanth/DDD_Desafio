package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class PaintType implements ValueObject<String> {

    private final String paintType;

    public PaintType(String paintType) {
        if(paintType.isBlank()) throw new IllegalArgumentException("paintType cannot be empty");
        this.paintType = Objects.requireNonNull(paintType, "paintType cannot be null");
    }

    @Override
    public String value() {
        return paintType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaintType paintType1 = (PaintType) o;

        return paintType != null ? paintType.equals(paintType1.paintType) : paintType1.paintType == null;
    }

    @Override
    public int hashCode() {
        return paintType != null ? paintType.hashCode() : 0;
    }
}
