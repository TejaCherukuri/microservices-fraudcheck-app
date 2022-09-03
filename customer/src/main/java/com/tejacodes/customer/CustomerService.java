package com.tejacodes.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest)
    {
        log.info("Entering CustomerService.registerCustomer()");
        Customer customer = Customer.builder().firstName(customerRegistrationRequest.getFirstName())
                                                .lastName(customerRegistrationRequest.getLastName())
                                                .email(customerRegistrationRequest.getEmail())
                                                .build();
        customerRepository.saveAndFlush(customer);

        log.info("Calling Fraud Microservice");
        FraudCheckResponse fraudCheckResponse =
                restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}",
                                    FraudCheckResponse.class,
                                    customer.getId());
        log.info("Returned from Fraud Microservice");
        if(fraudCheckResponse != null)
            if(fraudCheckResponse.isFraudster())
                throw new IllegalStateException("Customer is a Fraudster");

        log.info("Exiting CustomerService.registerCustomer()");

    }
}
