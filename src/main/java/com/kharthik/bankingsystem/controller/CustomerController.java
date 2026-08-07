package com.kharthik.bankingsystem.controller;
import org.springframework.web.bind.annotation.*;
import com.kharthik.bankingsystem.service.CustomerService;
import com.kharthik.bankingsystem.dto.CustomerDTO;
import java.util.List;
import com.kharthik.bankingsystem.entity.Customer;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public String saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);

    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);

    }
    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }


    }
