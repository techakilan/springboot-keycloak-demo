package com.westerly.frontend.controllers;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HelloController {
    @Autowired
    public KeycloakRestTemplate keycloakRestTemplate;

    @GetMapping("/")
    public String home() {
        return "home";

    }

    @GetMapping("/welcome")
    public String hello(Model model, KeycloakAuthenticationToken keycloakAuthenticationToken) {
        ResponseEntity<String> response = keycloakRestTemplate.getForEntity("http://localhost:9294/data", String.class);
        @SuppressWarnings("unchecked")
        KeycloakPrincipal<KeycloakSecurityContext> principal = (KeycloakPrincipal<KeycloakSecurityContext>) keycloakAuthenticationToken
                .getPrincipal();
        model.addAttribute("username", principal.getKeycloakSecurityContext().getToken().getFamilyName());
        model.addAttribute("message", response.getBody());
        return "hello";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";

    }
}
