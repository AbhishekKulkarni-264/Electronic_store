//package com.example.electronic_store.services.impl;
//
//import com.example.electronic_store.dtos.AddItemToCartRequest;
//import com.example.electronic_store.dtos.CartDto;
//import com.example.electronic_store.entities.Cart;
//import com.example.electronic_store.entities.CartItem;
//import com.example.electronic_store.entities.Product;
//import com.example.electronic_store.entities.User;
//import com.example.electronic_store.exceptions.ResourceNotFoundException;
//import com.example.electronic_store.repositories.CartItemRepository;
//import com.example.electronic_store.repositories.CartRepository;
//import com.example.electronic_store.repositories.ProductRepository;
//import com.example.electronic_store.repositories.UserRepository;
//import com.example.electronic_store.services.CartService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicReference;
//import java.util.stream.Collectors;
//
//@Service
//public class CartServiceImpl implements CartService {
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private ModelMapper mapper;
//    @Override
//    public CartDto addItemtoCart(String UserId, AddItemToCartRequest request) {
//
//        int quantity=request.getQuantity();
//        String productId=request.getProductId();
//
//        if(quantity<=0) throw  new RuntimeException("quantity not valid");
//
//       Product product= productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
//        //fetch user from db
//       User user= userRepository.findById(UserId).orElseThrow(()->new ResourceNotFoundException("User not found"));
//
//       Cart cart=null;
//       try{
//           cart=cartRepository.findByUser(user).get();
//       }catch(NoSuchElementException e){
//           cart=new Cart();
//           cart.setCartId(UUID.randomUUID().toString());
//           cart.setCreatedAt(new Date());
//       }
//        AtomicReference<Boolean> updat=new AtomicReference<>(false);
//       List<CartItem>items= cart.getItems();
//       List<CartItem>updatedItems= items.stream().map(item->{
//            if(item.getProduct().getProductId().equals(productId)){
//                item.setQuantity(quantity);
//                item.setPrice(quantity* product.getPrice());
//                updat.set(true);
//            }
//            return item;
//        }).collect(Collectors.toList());
//
//       cart.setItems(updatedItems);
//
//       if(!updat.get()) {
//           CartItem cartitem = CartItem.builder()
//                   .quantity(quantity)
//                   .price(quantity * product.getPrice())
//                   .cart(cart)
//                   .product(product)
//                   .build();
//
//           cart.getItems().add(cartitem);
//       }
//        cart.setUser(user);
//         Cart updated=cartRepository.save(cart);
//      return  mapper.map(updated,CartDto.class);
//    }
//
//    @Override
//    public void RemoveItemFromCart(String UserId, int cartItem) {
//       CartItem cartItem1= cartItemRepository.findById(cartItem).orElseThrow(()->new ResourceNotFoundException("Cart Item not found"));
//        cartItemRepository.delete(cartItem1);
//
//    }
//
//    @Override
//    public void clearCart(String UserId) {
//        User user= userRepository.findById(UserId).orElseThrow(()->new ResourceNotFoundException("User not found"));
//        Cart cart=cartRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("cart not found"));
//        cart.getItems().clear();
//        cartRepository.save(cart);
//    }
//
//    @Override
//    public CartDto getCartByUser(String userId) {
//        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
//        Cart cart=cartRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("cart not found"));
//        return mapper.map(cart,CartDto.class);
//    }
//}
