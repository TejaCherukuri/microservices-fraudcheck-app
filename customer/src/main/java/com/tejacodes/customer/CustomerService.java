package com.tejacodes.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest)
    {
        Customer customer = Customer.builder().firstName(customerRegistrationRequest.getFirstName())
                                                .lastName(customerRegistrationRequest.getLastName())
                                                .email(customerRegistrationRequest.getEmail())
                                                .build();
        customerRepository.save(customer);

    }
}
