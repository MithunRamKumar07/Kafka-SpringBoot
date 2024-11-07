package nl.rabobank.mithun.assessment.customer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

enum Status{
    ACTIVE,
    INACTIVE
}

@Entity
@Data
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customerSeqGenerator")
    @SequenceGenerator(name = "customerSeqGenerator", sequenceName = "customer_sequence", allocationSize = 1)
    int customerId;
    String userName;
    Timestamp createdAt;
    Status membershipStatus;
    String eventType;


}
