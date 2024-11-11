package nl.rabobank.mithun.assessment.timeline.repository;

import nl.rabobank.mithun.assessment.timeline.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**<p> Repository  to persist membership data into the Message DB</p>
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
}
