package nl.rabobank.mithun.assessment.customer.repository;

import nl.rabobank.mithun.assessment.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
