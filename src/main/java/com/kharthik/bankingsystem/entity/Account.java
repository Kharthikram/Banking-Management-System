package com.kharthik.bankingsystem.entity;
import jakarta.persistence.*;
import com.kharthik.bankingsystem.entity.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String accountNumber;
    private Double balance;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
