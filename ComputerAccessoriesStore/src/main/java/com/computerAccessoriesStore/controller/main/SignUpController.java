package com.computerAccessoriesStore.controller.main;

import com.computerAccessoriesStore.models.CreditCard;
import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.models.enums.Role;
import com.computerAccessoriesStore.service.CreditCardService;
import com.computerAccessoriesStore.service.UserService;
import com.computerAccessoriesStore.transfer.CreditCardDTO;
import com.computerAccessoriesStore.transfer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Calendar;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/signUp")
    public String registrationPage() {
        return "main/signUp";
    }

    @PostMapping("/signUp")
    public String registrationPage(@ModelAttribute UserDTO dto, BindingResult errors, Model model) {
        dto.setRole(Role.ROLE_BUYER);
        dto.setCreated_at(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        userService.add(dto);
        CreditCardDTO creditCard = CreditCardDTO.builder()
                .firsName(dto.getFirstName())
                .lastName(dto.getLastName())
                .card_code(0000000000000000f)
                .cvv("000")
                .month_year("00/00")
                .balance(0f)
                .idBuyer(userService.getUsername(dto.getUsername()).getId())
                .build();
        creditCardService.add(creditCard);
        return "redirect:/signIn";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "main/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassword(@ModelAttribute UserDTO dto, Model model) {
        User user = userService.getUsername(dto.getUsername());
        User bufuser = User.builder()
                .firstName(dto.getFirstName())
                .username(dto.getUsername())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
        String sb = "";
        if (user != null) {
            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";
            if (user.equals(bufuser)) {
                for (int i = 0; i < 9; i++) {
                    int index = (int) (AlphaNumericString.length()* Math.random());
                    sb+=AlphaNumericString.charAt(index);
                }
                model.addAttribute("password",sb);
                user.setPassword(sb);
                UserDTO userDTO = UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .created_at(user.getCreated_at())
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .build();
                userService.edit(userDTO);
                model.addAttribute("password",sb);
            }
        }
        return "main/forgotPassword";
    }
}
