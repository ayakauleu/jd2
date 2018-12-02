package com.yakauleu.ibank.web.controller;

import com.yakauleu.ibank.database.dto.CurrentUserDto;
import com.yakauleu.ibank.database.model.Card;
import com.yakauleu.ibank.database.model.Person;
import com.yakauleu.ibank.database.model.User;
import com.yakauleu.ibank.service.BoardService;
import com.yakauleu.ibank.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("currentUser")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MyUserService myUserService;

    @GetMapping("/board")
    private String show(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = myUserService.getByLogin(auth.getName());
        CurrentUserDto dto = CurrentUserDto.builder()
                .id(user.getId())
                .login(user.getLoginName())
                .name(user.getName())
                .role(user.getRole().toString())
                .build();
        model.addAttribute("currentUser", dto);
        List<Card> cards = boardService.getPersonCards((Person) user);
        model.addAttribute("cards", cards);
        return "board";
    }


}
