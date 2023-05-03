package org.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.ecommerce.domain.Cart;
import org.ecommerce.domain.Product;
import org.ecommerce.domain.User;
import org.ecommerce.domain.dto.ProductDto;
import org.ecommerce.mapper.ProductMapper;
import org.ecommerce.service.CartService;
import org.ecommerce.service.OrderService;
import org.ecommerce.service.ProductService;
import org.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ecommerce/carts")
@RequiredArgsConstructor
public class CartController {

    private final ProductMapper productMapper;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;



    @PostMapping(value = "/new/cart/{userId}")
    public ResponseEntity<?> createEmptyCart(@PathVariable Long userId) {
        try {
            User userOwningCart = userService.getUserById(userId);
            Cart newCart = new Cart(userOwningCart);
            cartService.saveCart(newCart);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Wystąpił błąd podczas tworzenia pustego koszyka dla użytkownika o id: " + userId);
        }
    }

    @GetMapping(value = "{cartId}")
    public ResponseEntity<List<ProductDto>> getAllProductsFromCart(@PathVariable Long cartId) {
        Cart loadedCart = cartService.findCart(cartId);
        List<Product> loadedProductList = loadedCart.getProductList();
        List<ProductDto> loadedProductDtoList = productMapper.mapToProductDtoList(loadedProductList);
        return ResponseEntity.ok(loadedProductDtoList);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "{cartId}")
    public ResponseEntity<List<ProductDto>> addProductToCartByID(@PathVariable Long cartId, @RequestBody List<Long> productIdList) {
        Cart loadedCart = cartService.findCart(cartId);
        List<Product> loadedProductList = loadedCart.getProductList();
        List<Product> addedProductList = new ArrayList<>();

        for (Long id : productIdList) {
            addedProductList.add(productService.getProduct(id));
        }

        List<Product> mergeProductList = new ArrayList<>();
        mergeProductList.addAll(loadedProductList);
        mergeProductList.addAll(addedProductList);
        loadedCart.setProductList(mergeProductList);
        cartService.saveCart(loadedCart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cartId}/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart loadedCart = cartService.findCart(cartId);
        List<Product> loadedProductList = loadedCart.getProductList();
        Product productToRemove = productService.getProduct(productId);
        loadedProductList.remove(productToRemove);
        loadedCart.setProductList(loadedProductList);
        cartService.saveCart(loadedCart);
        return ResponseEntity.ok().build();
    }

}