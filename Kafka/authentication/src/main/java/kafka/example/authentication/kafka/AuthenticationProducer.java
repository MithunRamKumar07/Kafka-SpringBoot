package kafka.example.authentication.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import kafka.example.authentication.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**<p> Kafka Producer to publish the events to Timeline Service. Publishes to the below topics</p>
 *  <li>AUTH</li>
 */
@Slf4j
@Service
public class AuthenticationProducer {

    private final KafkaTemplate<String, String> customerKafkaTemplate;

    @Autowired
    public AuthenticationProducer(KafkaTemplate<String, String> customerKafkaTemplate) {
        this.customerKafkaTemplate = customerKafkaTemplate;
    }

    public void publishEventToTimelineService(Object inputObject, String eventType, String topic) {
        CompletableFuture<SendResult<String, String>> future = customerKafkaTemplate.send(topic,getStringFromObject(inputObject));
        future.whenComplete((result,exception)->{
            if(exception!=null){
                log.info("The {} event could not be published to the topic : {} . Exception Cause : {} " ,
                        eventType, topic, exception.getMessage());
            }else{
                log.info("The {} event with body {} is published to the offset : {} ",
                        eventType,result.getProducerRecord().toString(),result.getRecordMetadata().offset());
            }
        });
    }

    private String getStringFromObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String eventAsString;
        try {
            eventAsString = objectMapper.writeValueAsString(object);
            log.info("Message to be published : {}" , eventAsString);
        } catch (JsonProcessingException e) {
            throw new AuthenticationException("Exception while parsing the JSON");
        }
        return eventAsString;
    }
}
