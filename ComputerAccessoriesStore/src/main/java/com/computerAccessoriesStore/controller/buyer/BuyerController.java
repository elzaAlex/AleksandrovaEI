package com.computerAccessoriesStore.controller.buyer;

import com.computerAccessoriesStore.models.*;
import com.computerAccessoriesStore.service.*;
import com.computerAccessoriesStore.transfer.ActDTO;
import com.computerAccessoriesStore.transfer.CommentDTO;
import com.computerAccessoriesStore.transfer.CreditCardDTO;
import com.computerAccessoriesStore.transfer.ProductDTO;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private ActService actService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam(value = "id", required = true) Long id, Model model) {
        Optional<Product> product = productService.getById(id);
        ProductDTO productDTO = ProductDTO.builder()
                .id(product.get().getId())
                .product_cost(product.get().getProduct_cost())
                .product_name(product.get().getProduct_name())
                .idSeller(product.get().getSeller().getId())
                .build();
        model.addAttribute("product", productDTO);
        User buyer = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("buyer", buyer);
        model.addAttribute("seller", product.get().getSeller());
        return "buyer/buyProduct";
    }

    @PostMapping(value = "/buyProduct")
    public String buyProduct(@ModelAttribute ActDTO dto, Model model) {
        Optional<Product> product = productService.getById(dto.getIdProduct());
        Float sum = product.get().getProduct_cost() * dto.getCount();
        Optional<User> seller = userService.getById(dto.getIdSeller());
        Optional<User> buyer = userService.getById(dto.getIdBuyer());
        List<CreditCard> creditCardBuyer = creditCardService.findAllByBuyerId(buyer.get().getId());
        List<CreditCard> creditCardSeller = creditCardService.findAllByBuyerId(seller.get().getId());
        if(creditCardBuyer.get(0).getBalance() - sum<0){
            return "redirect:/buyer/insufficientFunds";
        }else {
            creditCardBuyer.get(0).setBalance(creditCardBuyer.get(0).getBalance() - sum);
            creditCardSeller.get(0).setBalance(creditCardSeller.get(0).getBalance() + sum);
            creditCardService.edit(creditCardBuyer.get(0));
            creditCardService.edit(creditCardSeller.get(0));
            actService.add(dto);
        }
        return "redirect:/product/showProduct";
    }

    @GetMapping(value = "/personalAccount")
    public String personalAccount(Model model) {
        User buyer = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<CreditCard> creditCard = creditCardService.findAllByBuyerId(buyer.getId());
        model.addAttribute("card",creditCard.get(0).getBalance());
        model.addAttribute("buyer", buyer);
        return "buyer/personalAccount";
    }

    @GetMapping(value = "/shopList")
    public String shopList(Model model) {
        User buyer = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Act> actList = actService.findAllByBuyer(buyer.getId());
        model.addAttribute("acts", actList);
        return "buyer/shopList";
    }

    @GetMapping(value = "/listSellers")
    public String listSellers(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<User> sellers = userService.findAllBySellerInfoByParam(name);
        model.addAttribute("sellers", sellers);
        return "buyer/listSellers";
    }

    @GetMapping(value = "/leaveComment")
    public String leaveComment(@RequestParam(value = "id", required = true) Long id, Model model) {
        Optional<User> seller = userService.getById(id);
        model.addAttribute("idSeller", seller.get().getId());
        User buyer = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("idBuyer", buyer.getId());
        return "buyer/leaveComment";
    }

    @PostMapping(value = "/leaveComment")
    public String leaveComment(@ModelAttribute CommentDTO dto, Model model) {
        commentService.add(dto);
        return "redirect:/buyer/listSellers";
    }

    @GetMapping(value = "/showRating")
    public String showRating(@RequestParam(value = "id", required = true) Long id, Model model) {
        List<Comment> comments = commentService.getCommentSellerBySortDate(id);
        OptionalDouble rating = comments.stream().mapToDouble(e->e.getRating()).average();
        Optional<User> user = userService.getById(id);
        model.addAttribute("user",user.get());
        model.addAttribute("comments", comments);
        model.addAttribute("average",new java.text.DecimalFormat("0.00").format( rating.getAsDouble() ));
        return "buyer/showRating";
    }

    @GetMapping(value = "/insufficientFunds")
    public String insufficientFunds( Model model){
        return "buyer/insufficientFunds";
    }

    @PostMapping(value = "/insufficientFunds")
    public String insufficientFunds(@ModelAttribute CreditCardDTO dto){
        User buyer = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<CreditCard> creditCardBuyer = creditCardService.findAllByBuyerId(buyer.getId());
        creditCardBuyer.get(0).setBalance(dto.getBalance() +  creditCardBuyer.get(0).getBalance());
        creditCardService.edit(creditCardBuyer.get(0));
        return "redirect:/product/findProduct";
    }
}
