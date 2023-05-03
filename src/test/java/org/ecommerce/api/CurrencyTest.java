package org.ecommerce.api;

import org.ecommerce.api.domain.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrencyTest {

    @Test
    public void testCurrencyConstructor() {
        Currency currency = new Currency("USD", "EUR", 100.0, 87.0);
        Assertions.assertEquals("USD", currency.getBase());
        Assertions.assertEquals("EUR", currency.getTo());
        Assertions.assertEquals(100.0, currency.getAmount());
        Assertions.assertEquals(87.0, currency.getConvertedAmount());
        Assertions.assertNotNull(currency.getConvertDate());
    }

}