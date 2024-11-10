package nl.rabobank.mithun.assessment.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Data
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customerSeqGenerator")
    @SequenceGenerator(name = "customerSeqGenerator", sequenceName = "customer_sequence", allocationSize = 1)
    int customerId;
    String customerName;
    Timestamp createdAt;
    @Enumerated(EnumType.STRING)
    Status membershipStatus;
    String eventType;
}
