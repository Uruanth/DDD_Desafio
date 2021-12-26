package co.com.sofka.usecase;

import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.bicycle.values.BicycleId;
import co.com.sofka.domain.bicycle.values.ClientId;
import co.com.sofka.domain.events.ApprovedProduct;
import co.com.sofka.domain.performance.values.EngineerId;
import co.com.sofka.domain.performance.values.PilotId;
import co.com.sofka.domain.performance.values.QAId;
import co.com.sofka.services.SendEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotifyApprovedProductUsecaseTest {

    @Mock
    SendEmailService sendEmail;

    @Test
    void notifyApprovedProduct() {

        BicycleId bicicleId = BicycleId.from("bbbb");
        ClientId clientId = ClientId.from("cccc");
        EngineerId enginnerId = EngineerId.from("eeee");
        QAId qaId = QAId.from("qqqq");
        PilotId pilotId = PilotId.from("pppp");

        var event = new ApprovedProduct(bicicleId, clientId, enginnerId, qaId, pilotId);
        var usecase = new NotifyApprovedProductUsecase();

        when(sendEmail.sendEmail("email",
                clientId.value(),
                event.getAggregateName(),
                "The engineer id: " + event.getEnginnerId() + ", approved his request"))
                .thenReturn(true);

        ServiceBuilder builder = new ServiceBuilder();
        builder.addService(sendEmail);
        usecase.addServiceBuilder(builder);

        UseCaseHandler.getInstance()
                .syncExecutor(usecase, new TriggeredEvent<>(event))
        .orElseThrow();

        verify(sendEmail).sendEmail("email",
                clientId.value(),
                event.getAggregateName(),
                "The engineer id: " + event.getEnginnerId() + ", approved his request");
    }

}