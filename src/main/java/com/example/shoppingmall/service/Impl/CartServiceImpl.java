package com.example.shoppingmall.service.Impl;

import com.example.shoppingmall.data.dto.response.ResponseCart;
import com.example.shoppingmall.data.entity.Cart;
import com.example.shoppingmall.data.entity.Product;
import com.example.shoppingmall.data.entity.User;
import com.example.shoppingmall.data.repository.CartRepository;
import com.example.shoppingmall.data.repository.ProductRepository;
import com.example.shoppingmall.data.repository.UserRepository;
import com.example.shoppingmall.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    public static int ADD = 100;
    public static int DELETE = -100;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ResponseCart> readCart(String username) {
        List<ResponseCart> responseCartList = new ArrayList<>();
        ResponseCart responseCart;

        List<Cart> cartList = cartRepository.findAllCart(username);
        System.out.println(cartList);
        if(cartList.size() == 0) {
            return null;
        }

        for (Cart cart : cartList) {
            responseCart = new ResponseCart();
            responseCart.setId(cart.getProduct().getId());
            responseCart.setCount(cart.getCount());
            responseCart.setSize(cart.getProduct().getSize());
            responseCart.setName(cart.getProduct().getName());
            responseCart.setImgKey(cart.getProduct().getImgKey());
            responseCart.setPrice(cart.getProduct().getPrice());

            responseCartList.add(responseCart);
        }

        return responseCartList;
    }

    @Override
    public List<ResponseCart> createCart(String username, Long product_id) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(product_id).orElse(null);

        if(user == null || product == null) {
            return null;
        }

        Boolean check_same  = cartRepository.findSameCart(user.getId(), product_id, ADD);
        if(!check_same) {
            Cart cart = new Cart(null, 1, user, product);
            cartRepository.save(cart);
        }

        return readCart(user.getUsername());
    }

    @Override
    public List<ResponseCart> deleteCart(String username, Long product_id) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(product_id).orElse(null);

        if(user == null || product == null) {
            return null;
        }

        Boolean check_same  = cartRepository.findSameCart(user.getId(), product_id, DELETE);

        if(!check_same) {
            return null;
        }

        return readCart(user.getUsername());
    }
}
