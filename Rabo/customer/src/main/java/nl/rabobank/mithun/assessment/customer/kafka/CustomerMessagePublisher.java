package nl.rabobank.mithun.assessment.customer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.customer.model.Customer;
import nl.rabobank.mithun.assessment.customer.util.CustomerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CustomerMessagePublisher {

    private final KafkaTemplate<String, String>  customerKafkaTemplate;

    @Autowired
    public CustomerMessagePublisher(KafkaTemplate<String, String> customerKafkaTemplate) {
        this.customerKafkaTemplate = customerKafkaTemplate;
    }


    public void publishCustomerDataToAuthService(Object inputObject,String eventType,String topic) {
        CompletableFuture<SendResult<String, String>> future = customerKafkaTemplate.send(topic,getStringFromObject(inputObject));
        future.whenComplete((result,exception)->{
            if(exception!=null){
                log.info("The {} event could not be published to the topic : {} . Exception Cause : {} " ,
                        eventType, topic, exception.getMessage());
            }else{
                log.info("The {} event with body {} is published towards the topic {} to the offset : {} ",
                        eventType,result.getProducerRecord().toString(),topic,result.getRecordMetadata().offset());
            }
        });
    }

    private static String getStringFromObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage;
        try {
            jsonMessage = objectMapper.writeValueAsString(object);
            log.info("Message to be published : {}" , jsonMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonMessage;
    }
}
