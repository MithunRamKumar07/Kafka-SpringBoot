package kafka.example.authentication.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomerEvent {
    int customerId;
    String customerName;
    Timestamp createdAt;
    Status membershipStatus;
    String eventType;
}
