package nl.rabobank.mithun.assessment.authentication.repository;

import nl.rabobank.mithun.assessment.authentication.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Membership,Integer> {

    @Query("SELECT * FROM Membership WHERE customerId = ?")
    Membership getMemberDetailsByCustomerId(int customerId);
}
