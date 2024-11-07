package nl.rabobank.mithun.assessment.timeline.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.timeline.model.Message;
import nl.rabobank.mithun.assessment.timeline.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageListener {

    @Autowired
    TimelineService timelineService;

    @KafkaListener(topics = "AUTH_TOPIC",groupId = "timeline-group")
    public void listenMessages(String event){

        log.info("Listening events from Customer Service");
        ObjectMapper objectMapper = new ObjectMapper();
            Message message = null;
            try {
                message = objectMapper.readValue(event, Message.class);
                timelineService.saveMessage(message);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
    }
}