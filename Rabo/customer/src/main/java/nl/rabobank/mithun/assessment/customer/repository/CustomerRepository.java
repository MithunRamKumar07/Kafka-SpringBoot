package nl.rabobank.mithun.assessment.customer.repository;

import nl.rabobank.mithun.assessment.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**<p> Repository  to persist membership data into the Customer DB</p>
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
