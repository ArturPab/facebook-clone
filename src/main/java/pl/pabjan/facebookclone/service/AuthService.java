package pl.pabjan.facebookclone.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.facebookclone.controller.dto.RegisterRequest;
import pl.pabjan.facebookclone.exceptions.FacebookCloneException;
import pl.pabjan.facebookclone.model.User;
import pl.pabjan.facebookclone.model.VerificationToken;
import pl.pabjan.facebookclone.repo.UserRepository;
import pl.pabjan.facebookclone.repo.VerificationTokenRepository;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());
        user.setLastName(registerRequest.getLastName());
        user.setCity(registerRequest.getCity());
        user.setBirthday(registerRequest.getBirthday());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);

        emailService.sendmail(registerRequest.getEmail(),"Register", "Hello "+registerRequest.getName()+ "! Thanks for signing up! Activate your account by clicking the link below! \n"+ "http://localhost:8080/api/auth/accountVerification/" + token);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();

        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationTokenRepository.save(verificationToken);

        return token;
    }

    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() -> new FacebookCloneException("Wrong activate link!"));
        fetchUserAndEnable(verificationToken);
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new FacebookCloneException("Wrong email!"));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
