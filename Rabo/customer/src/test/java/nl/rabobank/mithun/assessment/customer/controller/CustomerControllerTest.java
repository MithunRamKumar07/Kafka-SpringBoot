package nl.rabobank.mithun.assessment.customer.controller;

import nl.rabobank.mithun.assessment.customer.model.Customer;
import nl.rabobank.mithun.assessment.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/** <p> Test class for {@link CustomerController }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @Mock
    Customer customer;

    @Mock
    List<Customer> customers;

    @Test
    public void testCreateCustomer(){
        when(customerService.createCustomer(any())).thenReturn(customer);
        assertNotNull(customerController.createCustomer(customer));
    }

    @Test
    public void testUpdateCustomer(){
        when(customerService.updateCustomer(any())).thenReturn(customer);
        assertNotNull(customerController.updateCustomer(customer));
    }

    @Test
    public void getAllCustomers(){
        when(customerService.getAllCustomers()).thenReturn(customers);
        assertNotNull(customerController.getAllCustomers());
    }

    @Test
    public void testGetCustomerById(){
        when(customerService.getCustomer(1)).thenReturn(customer);
        assertNotNull(customerController.getCustomer(1));
    }

    @Test
    public void testDeleteCustomerId(){
        customerController.deleteCustomer(1);
    }
}
