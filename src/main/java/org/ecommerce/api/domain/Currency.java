package org.ecommerce.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY")
public class Currency {
    @Id
    @NotNull
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "CONVERT_ID", nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "BASE")
    private String base;

    @NotNull
    @Column(name = "`TO`")
    private String to;

    @NotNull
    @Column(name = "AMOUNT")
    private Double amount;

    @NotNull
    @Column(name = "CONVERTEDAMOUNT")
    private Double convertedAmount;

    @NotNull
    @Column(name = "CONVERT_DATE")
    private LocalDate convertDate;

    public Currency(String base, String to, Double amount, Double convertedAmount) {
        this.base = base;
        this.to = to;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.convertDate = LocalDate.now();
    }
}