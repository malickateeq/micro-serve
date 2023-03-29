package com.malikatique.microserve.models;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class _User implements UserDetails {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    @NotNull
    @Pattern(regexp = "(PHONE|EMAIL)", message = "Verification Type must be either PHONE or EMAIL")
    private String verificationType;
    @Email
    private String email;
    @Size(min = 1, max = 4, message = "Invalid Country Code")
    private String countryCode;

    @Size(min = 8, max = 12, message = "Phone number is not valid")
    private String phone;

    private String otp;

    @Size(min = 6, max = 188)
    private String password;
    private Integer status = 1;

    private _Role role;

    // Validations
    @AssertTrue(message = "Email is required")
    public boolean isEmail() {
        if(this.getVerificationType().equals("EMAIL") && this.getEmail() == null) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Country Code is required")
    public boolean isCountryCode() {
        if(this.getVerificationType().equals("PHONE") && this.getCountryCode() == null) {
            return false;
        }
        return true;
    }

    @AssertTrue(message = "Phone is required")
    public boolean isPhone() {
        if(this.getVerificationType().equals("PHONE") && this.getPhone() == null) {
            return false;
        }
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List .of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
