package org.ecommerce.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.ecommerce.domain.Product;
import org.ecommerce.domain.User;

import java.util.List;

@Getter
@AllArgsConstructor
public class CartDto {
    private Long id;
    private User userLogin;
    private List<Product> productList;
}
