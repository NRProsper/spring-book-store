package dev.kiki.bookstore.auth;

import dev.kiki.bookstore.common.LoginResponse;
import dev.kiki.bookstore.dto.UserLoginDTO;
import dev.kiki.bookstore.models.User;
import dev.kiki.bookstore.repositories.UserRepository;
import dev.kiki.bookstore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public User authenticateUser(UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.email(),
                        userLoginDTO.password()
                )
        );

        return userRepository.findByEmail(userLoginDTO.email())
                .orElseThrow(() -> new RuntimeException("User not dound"));
    }

}
