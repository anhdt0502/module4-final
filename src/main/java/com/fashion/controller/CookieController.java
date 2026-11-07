package com.fashion.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {

    @GetMapping("/language")
    public String saveLanguage(
            @RequestParam String lang,
            HttpServletRequest request,
            HttpServletResponse response) {

        // Lưu ngôn ngữ vào Cookie
        Cookie cookie = new Cookie("language", lang);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");

        response.addCookie(cookie);

        // Lấy trang hiện tại
        String referer = request.getHeader("Referer");

        if (referer != null && !referer.isBlank()) {

            int queryIndex = referer.indexOf("?");

            if (queryIndex >= 0) {
                referer = referer.substring(0, queryIndex);
            }

            return "redirect:" + referer + "?lang=" + lang;
        }

        return "redirect:/products?lang=" + lang;
    }
}
