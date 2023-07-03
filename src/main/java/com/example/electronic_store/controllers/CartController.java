//package com.example.electronic_store.controllers;
//
//import com.example.electronic_store.dtos.AddItemToCartRequest;
//import com.example.electronic_store.dtos.CartDto;
//import com.example.electronic_store.services.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/carts")
//public class CartController {
//
//    @Autowired
//    private CartService cartService;
//
//    @PostMapping("/userId")
//    public ResponseEntity<CartDto> addItemToCart(@RequestBody AddItemToCartRequest request, @PathVariable("userId") String userId){
//       CartDto cartDto= cartService.addItemtoCart(userId,request);
//
//       return new ResponseEntity<>(cartDto, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{userId}/items/{itemId}")
//    public ResponseEntity<String> removeItemFromCart(@PathVariable("itemId") int itemId,@PathVariable("userId") String userId){
//     cartService.RemoveItemFromCart(userId,itemId);
//     return new ResponseEntity<>("item deleted",HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> clearCart(@PathVariable("userId") String userId){
//        cartService.clearCart(userId);
//        return new ResponseEntity<>("cart Cleared",HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/userId")
//    public ResponseEntity<CartDto> getCart( @PathVariable("userId") String userId){
//        CartDto cartDto= cartService.getCartByUser(userId);
//        return new ResponseEntity<>(cartDto, HttpStatus.OK);
//    }
//
//}
