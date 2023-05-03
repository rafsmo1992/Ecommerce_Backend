package org.ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import org.ecommerce.domain.Product;
import org.ecommerce.domain.dto.ProductDto;
import org.ecommerce.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProductMapper {
    private final GroupService groupService;

    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                groupService.getGroup(productDto.getGroupId())
        );
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getGroup().getId()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}

