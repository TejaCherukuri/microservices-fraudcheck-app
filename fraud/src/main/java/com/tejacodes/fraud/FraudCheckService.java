package com.tejacodes.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    // Constructor based Dependency Injection
    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    public boolean isFraudulentCustomer(Integer customerId)
    {
        log.info("Entering FraudCheckService.isFraudulentCustomer()");
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .build());
        log.info("Exiting FraudCheckService.isFraudulentCustomer()");
        return false;
    }
}
