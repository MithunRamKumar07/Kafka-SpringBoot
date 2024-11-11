package nl.rabobank.mithun.assessment.customer.controller;

import nl.rabobank.mithun.assessment.customer.service.CustomerService;
import nl.rabobank.mithun.assessment.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**<p> Controller class to handle the CRUD operations for the customer.</p>
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createCustomer(customer));
    }

    @GetMapping(value = "/get/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getCustomer(customerId));
    }
    @GetMapping(value = "/get")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getAllCustomers());
    }

    @PutMapping(value="/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.updateCustomer(customer));
    }

    @DeleteMapping(value="/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("The user has been deleted");
    }
}
