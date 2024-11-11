package nl.rabobank.mithun.assessment.authentication.repository;

import nl.rabobank.mithun.assessment.authentication.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**<p> Repository  to persist membership data into the Auth DB</p>
 */
@Repository
public interface AuthenticationRepository extends JpaRepository<Membership,Integer> {

    @Query(value = "SELECT * FROM Membership WHERE CUSTOMER_ID = :customerId",nativeQuery = true)
    Membership getMemberDetailsByCustomerId(int customerId);
}
