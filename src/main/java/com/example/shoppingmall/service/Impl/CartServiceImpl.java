package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.data.entity.Cart;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.repository.cart.CartRepository;
import com.example.shoppingmall.repository.product.ProductRepository;
import com.example.shoppingmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    public static int ADD = 100;
    public static int DELETE = -100;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ResponseCart> readCart(User user) {
        return cartRepository.findAllCart(user.getUsername());
    }

    @Override
    public List<ResponseCart> createCart(User user, Long product_id, String size) {
        Product product = productRepository.findById(product_id).orElse(null);

        if(user == null || product == null) {
            return null;
        }

        Boolean check_same = cartRepository.findSameCart(user.getId(), product_id, size, ADD);
        if(!check_same) {
            Cart cart = Cart.builder()
                    .id(null)
                    .count(1)
                    .user(user)
                    .product(product)
                    .size(size)
                    .build();
            cartRepository.save(cart);
        }

        return readCart(user);
    }

    @Override
    public List<ResponseCart> deleteCart(User user, Long product_id, String size) {
        Product product = productRepository.findById(product_id).orElse(null);

        if(user == null || product == null) {
            return null;
        }
        Boolean check_same = cartRepository.findSameCart(user.getId(), product_id, size, DELETE);

        if(!check_same) {
            return null;
        }

        return readCart(user);
    }

    @Override
    public List<ResponseCart> deleteAllCart(User user, Long product_id, String size) {
        Product product = productRepository.findById(product_id).orElse(null);

        if(user == null || product == null) {
            return null;
        }

        Boolean result = cartRepository.deleteAllCart(user.getId(), product_id, size);
        if(!result) {
            return null;
        }

        return readCart(user);
    }
}
