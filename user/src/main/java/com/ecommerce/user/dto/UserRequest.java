package com.ecommerce.user.dto;

import com.ecommerce.user.models.UserRole;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private UserRole role;
    private AddressDTO address;


}
