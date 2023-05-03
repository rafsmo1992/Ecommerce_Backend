package org.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.ecommerce.domain.Cart;
import org.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    public final CartRepository cartRepository;

    public List<Cart> findCarts() {
        return cartRepository.findAll();
    }

    public Cart findCart(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart saveCart (Cart cart){
        return cartRepository.save(cart);
    }

}