package kafka.example.authentication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Timeline {
    int timelineId;
    int customerId;
}
