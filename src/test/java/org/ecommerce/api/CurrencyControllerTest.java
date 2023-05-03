package org.ecommerce.api;

import org.ecommerce.api.config.CurrencyConfig;
import org.ecommerce.api.controller.CurrencyController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyControllerTest {

    @Autowired
    private CurrencyConfig currencyConfig;

    @Autowired
    private CurrencyController currencyController;

    @Test
    public void testConvertCurrency() {
        String base = "USD";
        String to = "EUR";
        Double amount = 100.0;

        String url = currencyConfig.getCurrencyConverterApiEndPoint() + "?apiKey=" + currencyConfig.getCurrencyConverterApiKey() + "&base=" + base + "&to=" + to + "&amount=" + amount;
        String response = currencyController.convertCurrency(base, to, amount);

        assertNotNull(response);
        assertTrue(response.contains("EUR"));

    }
}