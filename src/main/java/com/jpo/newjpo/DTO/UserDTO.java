package com.jpo.newjpo.DTO;


import com.jpo.newjpo.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private UserRole role;
    private Float argent;
}
