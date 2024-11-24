package kafka.example.timeline.repository;

import kafka.example.timeline.model.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**<p> Repository  to persist membership data into the Timeline DB</p>
 */
@Repository
public interface TimelineRepository extends JpaRepository<Timeline,Integer> {
}
