package com.tejacodes.fraud;

import com.tejacodes.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudCheckController {

    // Dependency injection happens via @AllArgsConstructor - Constructor based DI
    private final FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudulentCustomer(@PathVariable("customerId") Integer customerId)
    {
        log.info("Entering FraudCheckController");
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Exiting FraudCheckController");
        return new FraudCheckResponse(isFraudulentCustomer);
    }


}
