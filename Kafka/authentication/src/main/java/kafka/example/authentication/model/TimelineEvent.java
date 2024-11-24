package kafka.example.authentication.model;

import lombok.Data;

@Data
public class TimelineEvent {
    int timelineId;
    int customerId;
    String messageContent;
    String eventType;
    boolean isMembershipActive;

}