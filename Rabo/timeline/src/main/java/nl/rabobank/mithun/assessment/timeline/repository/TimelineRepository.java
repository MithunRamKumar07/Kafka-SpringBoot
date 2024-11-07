package nl.rabobank.mithun.assessment.timeline.repository;

import nl.rabobank.mithun.assessment.timeline.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimelineRepository extends JpaRepository<Message,Integer> {
}
