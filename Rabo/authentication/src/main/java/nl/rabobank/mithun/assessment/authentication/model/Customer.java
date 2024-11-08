package nl.rabobank.mithun.assessment.authentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

enum Status{
    ACTIVE,
    INACTIVE
}

@Data
@ToString
public class Customer {
    int customerId;
    String userName;
    Timestamp createdAt;
    Status membershipStatus;
    String operationType;
}
