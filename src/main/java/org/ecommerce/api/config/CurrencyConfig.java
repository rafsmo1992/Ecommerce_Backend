package org.ecommerce.api.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CurrencyConfig {
    @Value("${cc.api.endpoint}")
    private String currencyConverterApiEndPoint;

    @Value("${cc.api.key}")
    private String currencyConverterApiKey;
}
