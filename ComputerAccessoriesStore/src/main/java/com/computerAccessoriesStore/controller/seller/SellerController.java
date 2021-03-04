package com.computerAccessoriesStore.controller.seller;

import com.computerAccessoriesStore.models.*;
import com.computerAccessoriesStore.service.*;
import com.computerAccessoriesStore.transfer.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ActService actService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping(value = "/personalAccount")
    public String personalAccount(Model model) {
        User seller = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<CreditCard> creditCard = creditCardService.findAllByBuyerId(seller.getId());
        model.addAttribute("card",creditCard.get(0).getBalance());
        model.addAttribute("seller", seller);
        return "seller/personalAccount";
    }

    @GetMapping(value = "/listProduct")
    public String sellersProducts(Model model) {
        User seller = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Product> products = productService.findAllBySellerId(seller.getId());
        model.addAttribute("products", products);
        return "seller/listProduct";
    }

    @GetMapping(value = "/shopList")
    public String shopList(Model model) {
        User seller = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Act> actList = actService.findAllBySellerId(seller.getId());
        model.addAttribute("acts", actList);
        return "seller/shopList";
    }

    @GetMapping(value = "/sellerRating")
    public String showRating(Model model) {
        User seller = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Comment> comments = commentService.getCommentSellerBySortDate(seller.getId());
        OptionalDouble rating = comments.stream().mapToDouble(e -> e.getRating()).average();
        model.addAttribute("seller", seller);
        model.addAttribute("comments", comments);
        model.addAttribute("average", new java.text.DecimalFormat("0.00").format(rating.getAsDouble()));
        return "seller/showRating";
    }

    @GetMapping(value = "/histogramSales")
    public String histogramSales(Model model) {
        User seller = userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Float> stonks = actService.findSum(seller.getId());
        if (stonks.size() < 31) {
            for (int i = stonks.size(); i < 30; i++) {
                stonks.add(0f);
            }
        }
        model.addAttribute("stonks", stonks);
        return "seller/histogramSales";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "seller/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductDTO dto, BindingResult errors, Model model) {
        dto.setIdSeller(userService.getUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        productService.add(dto);
        return "redirect:/seller/listProduct";
    }
}
