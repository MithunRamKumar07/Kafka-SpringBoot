package nl.rabobank.mithun.assessment.timeline.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "messageSeqGenerator")
    @SequenceGenerator(name = "messageSeqGenerator", sequenceName = "message_sequence", allocationSize = 1)
    int messageId;
    String messageContent;
    int timelineId;
    String eventType;
}
