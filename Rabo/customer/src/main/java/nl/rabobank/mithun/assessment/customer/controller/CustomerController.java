package nl.rabobank.mithun.assessment.customer.controller;

import nl.rabobank.mithun.assessment.customer.service.CustomerService;
import nl.rabobank.mithun.assessment.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public void createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
    }

    @GetMapping(value = "/get/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        return customerService.getCustomer(customerId);
    }
    @GetMapping(value = "/get")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping(value="/update")
    public void updateCustomer(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
    }

    @DeleteMapping(value="/delete")
    public void deleteCustomer(int customerId){
        customerService.deleteCustomer(customerId);
    }
}
