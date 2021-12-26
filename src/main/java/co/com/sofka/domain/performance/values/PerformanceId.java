package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.Identity;

public class PerformanceId extends Identity {
    private PerformanceId(String value) {
        super(value);
    }

    public PerformanceId() {
    }

    public static PerformanceId from(String value) {
        return new PerformanceId(value);
    }
}
