package com.learning.board_0320.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(value = "/main")
    public String mainPage() {
        return "main";
    }

    @RequestMapping(value = "/login")
    public String getLogin(Model model) {
        model.addAttribute("kakaoApiKey", "64f8b2e7cd9422a4ffb9981f28ebf5c7");
        model.addAttribute("redirectUri", "127.0.0.1:9000/login/oauth/code/kakao");
        return "user/login";
    }

    @RequestMapping(value = "/login/oauth/code/kakao")
    public String getKakaoLogin(@RequestParam String code) {
        System.out.println("code::" + code);

        return "redirect:/login";
    }
}
