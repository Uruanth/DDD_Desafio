package co.com.sofka.usecase;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.performance.events.UnapprovedProduct;
import co.com.sofka.services.GetEmailService;
import co.com.sofka.services.SendEmailService;

import java.util.List;

public class NotifyUnapprovedProductUsecase extends UseCase<TriggeredEvent<UnapprovedProduct>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<UnapprovedProduct> requestEvent) {
        var event = requestEvent.getDomainEvent();
        var serviceEmail = getService(SendEmailService.class).orElseThrow();
        var getEmail = getService(GetEmailService.class).orElseThrow();
        var email = getEmail.getEmailById(event.getEnginnerId());

       var isValid = serviceEmail.sendEmail(
                email,
                event.getEnginnerId().value(),
                event.getAggregateName(),
                "The assigned product was not approved"

        );

       if (!isValid)
           throw new BusinessException(event.aggregateParentId(), "Error sending email");

        emit().onResponse(new ResponseEvents(List.of()));
    }
}
