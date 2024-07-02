package dev.kiki.bookstore.common;

import dev.kiki.bookstore.dto.UserResponseDTO;
import dev.kiki.bookstore.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponseDTO toUserDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
