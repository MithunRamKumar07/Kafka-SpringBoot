package nl.rabobank.mithun.assessment.authentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Timeline {
    @Id
    int timelineId;
    int customerId;
}
