package com.ecommerce.order.services;


import com.ecommerce.order.clients.ProductServiceClient;
import com.ecommerce.order.clients.UserServiceClient;
import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UserResponse;
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
    private final ProductServiceClient productServiceClient;
    private final UserServiceClient userServiceClient;

    public boolean addToCart(String userId, CartItemRequest request){


        ProductResponse productResponse = productServiceClient.getProductDetails(request.getProductId());
        if(productResponse == null || productResponse.getStockQuantity() < request.getQuantity()){
            return false;
        }

        UserResponse userResponse = userServiceClient.getUserById(userId);

        if(userResponse == null) return false;

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
