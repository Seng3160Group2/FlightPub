package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {

    @RequestMapping("test-session")
    public String getSession(HttpSession session, Model model) {
        if (session.getAttribute("visit-counter") == null) {
            session.setAttribute("visit-counter", "1");
        }
        model.addAttribute("session", session);
        return "test-session";
    }
}
