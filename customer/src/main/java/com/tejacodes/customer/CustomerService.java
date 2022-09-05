package com.tejacodes.customer;

import com.tejacodes.clients.fraud.FraudCheckResponse;
import com.tejacodes.clients.fraud.FraudClient;
import com.tejacodes.clients.notification.NotificationClient;
import com.tejacodes.clients.notification.NotificationRequest;
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

    @Autowired
    private FraudClient fraudCLient;

    @Autowired
    private NotificationClient notificationClient;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest)
    {
        log.info("Entering CustomerService.registerCustomer()");
        Customer customer = Customer.builder().firstName(customerRegistrationRequest.getFirstName())
                                                .lastName(customerRegistrationRequest.getLastName())
                                                .email(customerRegistrationRequest.getEmail())
                                                .build();
        customerRepository.saveAndFlush(customer);

        log.info("Calling Fraud Microservice");

        //Calling using Open Feign Rest Client
        FraudCheckResponse fraudCheckResponse =
                fraudCLient.isFraudulentCustomer(customer.getId());

        log.info("Returned from Fraud Microservice");
        if(fraudCheckResponse != null)
            if(fraudCheckResponse.isFraudster())
                throw new IllegalStateException("Customer is a Fraudster");
            else
            {
                log.info("Calling Notification Microservice");
                // Calling using Open feign rest client
                String result = notificationClient.sendNotification(
                        new NotificationRequest(customer.getId(),
                                                customer.getEmail(),
                                                String.format("Hello %s, Welcome", customer.getFirstName()))
                );
                log.info(result);
                log.info("Returned from Notification Microservice");
            }

        log.info("Exiting CustomerService.registerCustomer()");

    }
}
