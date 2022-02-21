package com.bzada.gestimowebapi.Controllers;

import com.bzada.gestimowebapi.Dtos.RegisterRequest;
import com.bzada.gestimowebapi.Services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("L'utilisateur a été enregistré avec succès",OK);
    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Le compte est activé avec succès",OK);
    }
}
