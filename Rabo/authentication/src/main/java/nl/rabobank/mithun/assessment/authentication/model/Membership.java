package nl.rabobank.mithun.assessment.authentication.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "membershipSeqGenerator")
    @SequenceGenerator(name = "membershipSeqGenerator", sequenceName = "membership_sequence", allocationSize = 1)
    int membershipId;
    int customerId;
    Timestamp createdAt; // Confirm the data type
    Status membershipStatus;
}
