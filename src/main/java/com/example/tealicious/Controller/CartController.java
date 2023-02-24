//package com.example.tealicious.Controller;
//
//import com.example.tealicious.Entity.Cart;
//import com.example.tealicious.Pojo.CartPojo;
//import com.example.tealicious.Services.CartService;
//import com.example.tealicious.Services.MenuService;
//import com.example.tealicious.Services.UserService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.security.Principal;
//import java.util.List;
//
//
//    @Controller
//    @RequiredArgsConstructor
//    @RequestMapping("/cart")
//    public class CartController {
//        private final CartService cartService;
//
//        private final MenuService menuService;
//        private final UserService userService;
//
//        @GetMapping("/addCart")
//        public String displayCart(Principal principal, Model model , CartPojo cartPojo){
//            Integer id = userService.findByEmail(principal.getName()).getId();
//            List<Cart> list = cartService.fetchAll(id);
//
//
//            model.addAttribute("cart",  cartPojo);
//            model.addAttribute("cartItems", list);
//
//            return "/cart";
//        }
//
//        @GetMapping("/add/{id}")
//        public String saveToCart(@PathVariable Integer id, Principal principal){
//            cartService.saveToCart(id, principal);
//            return "redirect:/shop";
//        }
//
//        @PostMapping("/updateQuantity/{id}")
//        public String updateQuantity(@Valid CartPojo cartPojo){
//            Cart cart = cartService.fetchOne(cartPojo.getId());
//            cartService.updateQuantity(cart);
//            return "redirect:/cart";
//        }
//
////    @PostMapping("/updateQuantity/{id}")
////    public String updateQuantity(@Valid ProductPojo productPojo){
////        Cart cart = cartService.fetchOne(productPojo.getId());
//////        cart.setQuantity(productPojo.getQuantity());
////        productService.updateQuantity(cart);
////        return "redirect:/cart";
////    }
//
//        @GetMapping("/remove/{id}")
//        public String deleteCartItem(@PathVariable("id") Integer id){
//            cartService.deleteFromCart(id);
//            return "redirect:/cart/addCart";
//        }
//
//
//
//        @GetMapping("/checkout")
//        public String getCheckout() {
//            return "checkout";
//        }
//    }
//
