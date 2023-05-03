package org.ecommerce.api;

import org.ecommerce.api.config.CurrencyConfig;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class CurrencyConfigTest {

    @Test
    public void testCurrencyConverterApiEndPoint() {
        CurrencyConfig config = new CurrencyConfig();
        config.setCurrencyConverterApiEndPoint("testURL");
        assertEquals("testURL", config.getCurrencyConverterApiEndPoint());
    }

    @Test
    public void testCurrencyConverterApiKey() {
        CurrencyConfig config = new CurrencyConfig();
        config.setCurrencyConverterApiKey("API_KEY");
        assertEquals("API_KEY", config.getCurrencyConverterApiKey());
    }

}