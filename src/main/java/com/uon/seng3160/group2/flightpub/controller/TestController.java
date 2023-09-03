package com.uon.seng3160.group2.flightpub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

@Slf4j
@Controller
public class TestController {

    @RequestMapping("test-session")
    public String getSession(HttpSession session) {
        if (session.getAttribute("visit-counter") == null) {
            session.setAttribute("visit-counter", "1");
            log.info("New user ");
        } 
        return "test-session";
    }
}
