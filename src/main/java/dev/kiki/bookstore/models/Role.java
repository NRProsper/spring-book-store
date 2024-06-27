package dev.kiki.bookstore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties("roles")
    @JsonBackReference
    private List<User> users;

    @Override
    public String getAuthority() {
        return role;
    }

}
