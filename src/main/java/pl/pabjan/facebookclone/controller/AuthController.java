package pl.pabjan.facebookclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.facebookclone.controller.dto.LoginRequest;
import pl.pabjan.facebookclone.controller.dto.RegisterRequest;
import pl.pabjan.facebookclone.service.AuthService;

@AllArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("Registration succesfully! Check your mail!", HttpStatus.OK);
    }

    @GetMapping("/api/auth/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
    }
}
