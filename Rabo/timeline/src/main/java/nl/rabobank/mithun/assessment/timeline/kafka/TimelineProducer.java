package nl.rabobank.mithun.assessment.timeline.kafka;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.timeline.util.TimelineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class TimelineProducer {

    private final KafkaTemplate<String, String> timelineKafkaTemplate;

    @Autowired
    public TimelineProducer(KafkaTemplate<String, String> customerKafkaTemplate) {
        this.timelineKafkaTemplate = customerKafkaTemplate;
    }

    public void publishEventToAuthService(Object inputObject, String eventType, String topic) {
        CompletableFuture<SendResult<String, String>> future = timelineKafkaTemplate
                .send(topic,TimelineUtils.getStringFromObject(inputObject));
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
}
