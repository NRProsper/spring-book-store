package dev.kiki.bookstore.services;

import dev.kiki.bookstore.dto.RegisterUserDTO;
import dev.kiki.bookstore.models.Role;
import dev.kiki.bookstore.models.User;
import dev.kiki.bookstore.repositories.RoleRepository;
import dev.kiki.bookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public User createUser(RegisterUserDTO userDTO) {
        //Mapping userDTO to User
        //All users will have a USER role except one that is created ant the Initialization of the database
        Role role = roleRepository.findByRoleIgnoreCase("User").get();
        User user = new User();
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }
}
