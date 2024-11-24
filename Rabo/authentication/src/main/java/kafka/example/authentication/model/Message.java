package kafka.example.authentication.model;

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
