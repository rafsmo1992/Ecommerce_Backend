package org.ecommerce.api;


import org.ecommerce.api.domain.dto.CurrencyDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class CurrencyDtoTest {

    @Test
    public void testCurrencyDto() {
        CurrencyDto currencyDto = new CurrencyDto(1L, "USD", 100.0, 80.0, LocalDate.now());

        assertEquals(1L, currencyDto.getId());
        assertEquals("USD", currencyDto.getTo());
        assertEquals(100.0, currencyDto.getAmount());
        assertEquals(80.0, currencyDto.getConvertedAmount());
        assertEquals(LocalDate.now(), currencyDto.getConvertDate());
    }
}