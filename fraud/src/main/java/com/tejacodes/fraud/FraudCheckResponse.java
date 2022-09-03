package com.tejacodes.fraud;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FraudCheckResponse {

    private boolean isFraudulentCustomer;
}
