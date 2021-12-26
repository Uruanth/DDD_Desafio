package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.events.ApprovedProduct;
import co.com.sofka.services.GetEmailService;
import co.com.sofka.services.SendEmailService;

import java.util.List;

public class NotifyApprovedProductUsecase extends UseCase<TriggeredEvent<ApprovedProduct>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<ApprovedProduct> requestEvent) {
        var event = requestEvent.getDomainEvent();
        var service = getService(SendEmailService.class).orElseThrow();
        var getEmailService = getService(GetEmailService.class).orElseThrow();

        boolean isValid = service.sendEmail(getEmailService.getEmailById(event.getClientId()),
                event.getClientId().value(),
                event.getAggregateName(),
                "The engineer id: " + event.getEnginnerId() + ", approved his request");

        if (!isValid)
            throw new BusinessException(event.getBicycleId().value(), "Error sending email");

        emit().onResponse(new ResponseEvents(List.of()));
    }
}
