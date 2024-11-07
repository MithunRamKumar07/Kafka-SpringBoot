package nl.rabobank.mithun.assessment.customer.config;

import nl.rabobank.mithun.assessment.customer.util.CustomerConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public NewTopic createCustomerTopic(){
        return new NewTopic(CustomerConstants.CUSTOMER_TOPIC,3,(short) 1);
    }
}
