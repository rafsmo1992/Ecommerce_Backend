package org.ecommerce.api.service;

import org.ecommerce.api.domain.Currency;
import org.ecommerce.api.repository.CurrencyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
        this.restTemplate = new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Currency convertCurrency(String base, String to, Double amount) {
        String url = "https://anyapi.io/api/v1/exchange/convert?apiKey=eodq8fudb5odc3bqnadqdgdpnjhpn4bogpl8q743g94d6f875a0ia8&base=" + base + "&to=" + to + "&amount=" + amount;
        String response = restTemplate.getForObject(url, String.class);
        Double convertedAmount = Double.parseDouble(response);
        Currency currency = new Currency(base, to, amount, convertedAmount);
        currencyRepository.save(currency);
        return currency;
    }
}
