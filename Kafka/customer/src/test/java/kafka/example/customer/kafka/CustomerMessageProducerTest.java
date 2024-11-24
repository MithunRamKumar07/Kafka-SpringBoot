package kafka.example.customer.kafka;

import kafka.example.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
/** <p> Test class for {@link CustomerMessageProducer }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class CustomerMessageProducerTest {

    @InjectMocks
    CustomerMessageProducer customerMessageProducer;

    @Mock
    CompletableFuture<SendResult<String, String>> future;

    @Mock
    KafkaTemplate<String,String> customerKafkaTemplate;

    @Mock
    Customer customer;

    @Test
    public void testPublish(){
        when(customerKafkaTemplate.send(any(),any())).thenReturn(future);
        customerMessageProducer.publishCustomerDataToAuthService(customer,
                "createCustomer","CUSTOMER");
    }
}
