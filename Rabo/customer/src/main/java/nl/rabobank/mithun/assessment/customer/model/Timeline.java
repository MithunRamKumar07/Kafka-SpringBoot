package nl.rabobank.mithun.assessment.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Timeline {
    @Id
    int timelineId;
    int customerId;
}
