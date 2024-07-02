package dev.kiki.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterUserDTO(
        @NotBlank(message = "First name attribute is required")
        String firstName,

        @NotBlank(message = "Last name attribute is required")
        String lastName,

        @NotBlank(message = "Email is required.")
        @Email(message = "Email should be valid")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is required.")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                message = "Password must contain at least one digit, one uppercase letter, one lowercase letter, one special character, and be at least 8 characters long.")
        String password
) {
}
