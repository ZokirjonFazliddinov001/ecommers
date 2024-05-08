package uz.pdp.ecommers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.ecommers.enums.Role;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private UUID uuid;
    private String userName;
    private String password;
    private String photo;
    private Role role;
}
