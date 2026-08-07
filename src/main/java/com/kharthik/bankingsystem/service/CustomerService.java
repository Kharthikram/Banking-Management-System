package com.kharthik.bankingsystem.service;
import org.springframework.stereotype.Service;
import com.kharthik.bankingsystem.repository.CustomerRepository;
import com.kharthik.bankingsystem.dto.CustomerDTO;
import com.kharthik.bankingsystem.entity.Customer;
import java.util.List;
import com.kharthik.bankingsystem.exception.CustomerNotFoundException;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String testService() {
        return "Customer Service Working";
    }

    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());
        customerRepository.save(customer);

        return "Customer Saved Successfully";

    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();

    }

    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found"));
    }

    public String updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found"));
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setAddress(customerDTO.getAddress());

        customerRepository.save(customer);

        return "Customer Updated Successfully";

    }

    public String deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer Not Found"));
        customerRepository.delete(customer);

        return "Customer Deleted Successfully";


    }
}
