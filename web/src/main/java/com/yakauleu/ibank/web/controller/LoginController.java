package com.yakauleu.ibank.web.controller;

import com.yakauleu.ibank.database.dto.CurrentUserDto;
import com.yakauleu.ibank.database.dto.PersonRegisterDto;
import com.yakauleu.ibank.database.model.Person;
import com.yakauleu.ibank.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

    @Autowired
    private MyUserService myUserService;

    @GetMapping("/my-login")
    public String getLoginPage() {
        return "my-login";
    }

    @PostMapping("/my-login")
    public String getStartPage(CurrentUserDto currentUser, Model model) {
        model.addAttribute("currentUser", currentUser);
        String initialPage;
        if ("ADMIN".equals(currentUser.getRole())) {
            initialPage = "payments";
        } else {
            initialPage = "board";
        }
        return "redirect:/" + initialPage;
    }

    @GetMapping("/signup")
    public String getSignup(Model model) {

        Person person = new Person();
        model.addAttribute("newPerson", person);
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(PersonRegisterDto newPerson) {
        myUserService.createNew(newPerson);
        return "redirect:/my-login";
    }


//    @GetMapping("/logout")
//    public String getLogoutPage() {
//        return "redirect:/my-login";
//    }

}
