package dev.kiki.bookstore.dto;

import dev.kiki.bookstore.models.Role;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
