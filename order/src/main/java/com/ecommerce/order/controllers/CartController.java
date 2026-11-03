package com.ecommerce.order.controllers;

import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.services.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Transactional
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request){

        if(!cartService.addToCart(userId,request)){
            return ResponseEntity.badRequest().body("Quantity is not enough or User is not found or Product is not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> deleteFromCart(@RequestHeader("X-User-ID") String userId,
                                               @PathVariable String productId){

        boolean deleted = cartService.deleteFromCart(userId,productId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(cartService.getCart(userId));
    }
}
