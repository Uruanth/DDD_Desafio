package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Technique implements ValueObject<String> {
    private final String paintTechnique;

    public Technique(String paintTechnique) {
        if(paintTechnique.isBlank()) throw new IllegalArgumentException("paintTechnique cannot be empty");
        this.paintTechnique = Objects.requireNonNull(paintTechnique, "paintTechnique cannot be null");
    }

    @Override
    public String value() {
        return paintTechnique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Technique technique = (Technique) o;

        return paintTechnique != null ? paintTechnique.equals(technique.paintTechnique) : technique.paintTechnique == null;
    }

    @Override
    public int hashCode() {
        return paintTechnique != null ? paintTechnique.hashCode() : 0;
    }
}
