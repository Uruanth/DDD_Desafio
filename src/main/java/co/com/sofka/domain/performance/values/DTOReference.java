package co.com.sofka.domain.performance.values;

import co.com.sofka.domain.generic.ValueObject;

public class DTOReference implements ValueObject<RequestPerformance> {


    private final RequestPerformance referenceData;

    public DTOReference(RequestPerformance referenceData) {
        this.referenceData = referenceData;
    }
    @Override
    public RequestPerformance value() {
        return referenceData;
    }
}
