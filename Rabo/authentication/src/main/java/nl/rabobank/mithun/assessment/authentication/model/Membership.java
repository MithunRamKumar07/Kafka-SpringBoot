package nl.rabobank.mithun.assessment.authentication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "membershipSeqGenerator")
    @SequenceGenerator(name = "membershipSeqGenerator", sequenceName = "membership_sequence", allocationSize = 1)
    int membershipId;
    int customerId;
    Timestamp createdAt;
    @Enumerated(EnumType.STRING)
    Status membershipStatus;
}
