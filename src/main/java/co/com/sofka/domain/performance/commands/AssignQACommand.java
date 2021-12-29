package co.com.sofka.domain.performance.commands;

import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generics.PersonalInformation;
import co.com.sofka.domain.performance.values.PerformanceId;
import co.com.sofka.domain.performance.values.QAId;
import co.com.sofka.domain.performance.values.RequestPerformance;

public class AssignQACommand extends Command {

    private final PerformanceId performanceId;
    private final QAId qaId;
    private final PersonalInformation personalInformation;
    private final RequestPerformance order;

    public PerformanceId getPerformanceId() {
        return performanceId;
    }

    public QAId getQaId() {
        return qaId;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public RequestPerformance getOrder() {
        return order;
    }

    public AssignQACommand(PerformanceId performanceId, QAId qaId, PersonalInformation personalInformation, RequestPerformance order) {
        this.performanceId = performanceId;
        this.qaId = qaId;
        this.personalInformation = personalInformation;
        this.order = order;
    }
}
