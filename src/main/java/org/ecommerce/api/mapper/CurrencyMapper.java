package org.ecommerce.api.mapper;

import org.ecommerce.api.domain.Currency;
import org.ecommerce.api.domain.dto.CurrencyDto;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper {

    public CurrencyDto currencyToCurrencyDto(Currency currency) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(currency.getId());
        currencyDto.setTo(currency.getTo());
        currencyDto.setAmount(currency.getAmount());
        currencyDto.setConvertedAmount(currency.getConvertedAmount());
        currencyDto.setConvertDate(currency.getConvertDate());
        return currencyDto;
    }
}