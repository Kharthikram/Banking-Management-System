package com.kharthik.bankingsystem.repository;
import com.kharthik.bankingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {

}
