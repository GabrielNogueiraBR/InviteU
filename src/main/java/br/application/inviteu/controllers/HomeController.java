package br.application.inviteu.controllers;

import br.application.inviteu.entities.User;
import br.application.inviteu.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/login")
    public ResponseEntity<User> login(){
        return ResponseEntity.ok().body(userLoginService.getUserLogged());
    }
}
