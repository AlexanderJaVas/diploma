package com.example.diploma.services;

import com.example.diploma.models.Cart;
import com.example.diploma.repositories.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getCartByPersonId(int id) {
        return cartRepository.findByPersonId(id);
    }

    @Transactional
    public void deleteCartByItemId(int id) {
        cartRepository.deleteCartByItemId(id);
    }

    @Transactional
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public boolean itemByIdInCarts(int id) {
        List<Cart> optionalCart = cartRepository.findCartsByItemId(id);
        return !optionalCart.isEmpty();
    }
}
