package org.ecommerce.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UserDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("userLogin")
    private String userLogin;
    @JsonProperty("userPassword")
    private String userPassword;
    @JsonProperty("userEmail")
    private String userEmail;
    @JsonProperty("signUpDate")
    private LocalDate signUpDate;

}