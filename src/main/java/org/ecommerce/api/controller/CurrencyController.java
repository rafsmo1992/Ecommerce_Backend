package org.ecommerce.api.controller;

import lombok.RequiredArgsConstructor;
import org.ecommerce.api.config.CurrencyConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyConfig currencyConfig;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/convert")
    public String convertCurrency(
            @RequestParam("base") String base,
            @RequestParam("to") String to,
            @RequestParam("amount") Double amount
    ) {
        String url = currencyConfig.getCurrencyConverterApiEndPoint() + "?apiKey=" + currencyConfig.getCurrencyConverterApiKey() + "&base=" + base + "&to=" + to + "&amount=" + amount;
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }
}
