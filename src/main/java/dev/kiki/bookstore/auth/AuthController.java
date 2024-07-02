package dev.kiki.bookstore.auth;

import dev.kiki.bookstore.common.LoginResponse;
import dev.kiki.bookstore.common.UserMapper;
import dev.kiki.bookstore.dto.RegisterUserDTO;
import dev.kiki.bookstore.dto.UserLoginDTO;
import dev.kiki.bookstore.dto.UserResponseDTO;
import dev.kiki.bookstore.jwt.JwtTokenService;
import dev.kiki.bookstore.models.User;
import dev.kiki.bookstore.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;
    private final UserMapper userMapper;

    @PostMapping("/signup")
    public UserResponseDTO register(@Valid @RequestBody RegisterUserDTO userDTO) {
        return userMapper.toUserDTO(userService.createUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid  @RequestBody UserLoginDTO userLoginDTO) {
        User authenticatedUser = authService.authenticateUser(userLoginDTO);
        String token = jwtTokenService.generateToken(authenticatedUser);
        LoginResponse response = new LoginResponse(token, jwtTokenService.expirationTime());

        return ResponseEntity.ok(response);
    }

}
