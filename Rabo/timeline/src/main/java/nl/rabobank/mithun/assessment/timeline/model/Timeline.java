package nl.rabobank.mithun.assessment.timeline.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Timeline {
    @Id
    int timelineId;
    int customerId;
}
