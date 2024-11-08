package nl.rabobank.mithun.assessment.timeline.model;

import lombok.Data;

@Data
public class MessageEvent {
    int messageId;
    String messageContent;
    Timeline timeline;
    String eventType;
}