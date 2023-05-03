package org.ecommerce.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CurrencyDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("to")
    private String to;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("convertedAmount")
    private Double convertedAmount;
    @JsonProperty("convertDate")
    private LocalDate convertDate;

}