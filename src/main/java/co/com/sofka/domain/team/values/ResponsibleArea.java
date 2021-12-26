package co.com.sofka.domain.team.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class ResponsibleArea implements ValueObject<ResponsibleArea.Properties> {

    private final Integer area;
    private final Integer staffAmount;

    public ResponsibleArea(Integer area, Integer staffAmount) {
        if(area<0) throw new IllegalArgumentException("area out of range");
        if(staffAmount<0) throw new IllegalArgumentException("staffAmount out of range");

        this.area = Objects.requireNonNull(area, "Area cannot be null");
        this.staffAmount = Objects.requireNonNull(staffAmount, "StaffAmount cannot be null");
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public Integer area() {
                return area;
            }

            @Override
            public Integer staffAmount() {
                return staffAmount;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponsibleArea that = (ResponsibleArea) o;

        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        return staffAmount != null ? staffAmount.equals(that.staffAmount) : that.staffAmount == null;
    }

    @Override
    public int hashCode() {
        int result = area != null ? area.hashCode() : 0;
        result = 31 * result + (staffAmount != null ? staffAmount.hashCode() : 0);
        return result;
    }

    public interface Properties {
        Integer area();
        Integer staffAmount();
    }
}
