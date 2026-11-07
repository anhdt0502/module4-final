package com.fashion.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {

    @GetMapping("/language")
    public String saveLanguage(
            @RequestParam String lang,
            HttpServletResponse response) {

        Cookie cookie = new Cookie("language", lang);

        cookie.setMaxAge(60 * 60 * 24 * 30);

        cookie.setPath("/");

        response.addCookie(cookie);

        return "redirect:/products?lang=" + lang;
    }
}