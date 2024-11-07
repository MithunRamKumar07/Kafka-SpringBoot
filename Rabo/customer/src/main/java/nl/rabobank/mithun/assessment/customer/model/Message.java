package nl.rabobank.mithun.assessment.customer.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
public class Message {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "messageSeqGenerator")
//    @SequenceGenerator(name = "messageSeqGenerator", sequenceName = "message_sequence", allocationSize = 1)
    int messageId;
    String messageContent;
    Timeline timeline;
    String eventType;
}
