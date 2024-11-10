package nl.rabobank.mithun.assessment.timeline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineEvent {
    int timelineId;
    int customerId;
    String messageContent;
    String eventType;
    boolean isMembershipActive;

}