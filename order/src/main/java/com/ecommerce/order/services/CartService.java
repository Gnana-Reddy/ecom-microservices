package com.ecommerce.order.services;


import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepo;

    public boolean addToCart(String userId, CartItemRequest request){

//        Optional<Product> productOpt = productRepo.findById(request.getProductId());
//
//        if(productOpt.isEmpty()){
//            return false;
//        }
//
//        Product existingProduct = productOpt.get();
//
//        if(existingProduct.getStockQuantity() < request.getQuantity()){
//            return false;
//        }
//
//        Optional<User> userOpt = userRepo.findById(Long.valueOf(userId));
//
//        if(userOpt.isEmpty()){
//            return false;
//        }
//        User existingUser = userOpt.get();

        CartItem existingItem = cartItemRepo.findByUserIdAndProductId(userId,request.getProductId());

        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity()+ request.getQuantity());
            existingItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepo.save(existingItem);
        }else{

            CartItem item = new CartItem();
            item.setUserId(userId);
            item.setProductId(request.getProductId());
            item.setQuantity(request.getQuantity());
            item.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepo.save(item);
        }
        return true;
    }

    public boolean deleteFromCart(String userId, String productId) {

        CartItem cartItem= cartItemRepo.findByUserIdAndProductId(userId,productId);

        if(cartItem != null){
            cartItemRepo.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(String userId){
        return cartItemRepo.findByUserId(userId);
    }

    public void clearCart(String userId){
            cartItemRepo.deleteByUserId(userId);
    }
}
