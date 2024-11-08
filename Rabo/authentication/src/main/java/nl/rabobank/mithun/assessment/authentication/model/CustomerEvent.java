package nl.rabobank.mithun.assessment.authentication.model;

import lombok.Data;

import java.sql.Timestamp;

enum Status{
    ACTIVE,
    INACTIVE
}

@Data
public class CustomerEvent {
    int customerId;
    String customerName;
    Timestamp createdAt;
    Status membershipStatus;
    String eventType;
}
