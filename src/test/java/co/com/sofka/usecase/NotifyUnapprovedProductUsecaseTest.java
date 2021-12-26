package co.com.sofka.usecase;

import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.events.ApprovedProduct;
import co.com.sofka.domain.events.UnapprovedProduct;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.PilotId;
import co.com.sofka.domain.performance.values.QAId;
import co.com.sofka.services.GetEmailService;
import co.com.sofka.services.SendEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotifyUnapprovedProductUsecaseTest {

    @Mock
    SendEmailService sendEmail;

    @Mock
    GetEmailService getEmailService;

    @Test
    void notifyUnapprovedProduct() {

        BicycleId bicicleId = BicycleId.from("bbbb");
        EngineerId enginnerId = EngineerId.from("eeee");
        QAId qaId = QAId.from("qqqq");
        PilotId pilotId = PilotId.from("pppp");

        var event = new UnapprovedProduct(bicicleId, enginnerId, qaId, pilotId);
        var usecase = new NotifyUnapprovedProductUsecase();

        when(getEmailService.getEmailById(enginnerId)).thenReturn("correo ingeniero");

        when(sendEmail.sendEmail(getEmailService.getEmailById(enginnerId),
                enginnerId.value(),
                event.getAggregateName(),
                "The assigned product was not approved"))
                .thenReturn(true);

        ServiceBuilder builder = new ServiceBuilder();
        builder.addService(sendEmail);
        builder.addService(getEmailService);
        usecase.addServiceBuilder(builder);

        UseCaseHandler.getInstance()
                .syncExecutor(usecase, new TriggeredEvent<>(event))
                .orElseThrow();


        verify(sendEmail).sendEmail(getEmailService.getEmailById(enginnerId),
                enginnerId.value(),
                event.getAggregateName(),
                "The assigned product was not approved");
        verify(getEmailService, times(3)).getEmailById(enginnerId);
    }


}