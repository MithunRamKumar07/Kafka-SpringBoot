package kafka.example.customer.repository;

import kafka.example.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**<p> Repository  to persist membership data into the Customer DB</p>
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
