package com.malikatique.microserve.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "otps")
public class Otp {
    @Id
    private String id;
    @DBRef
    private User user;

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
    private String validFor;
    private Integer attempts;
    private String lastAttemptAt;
    private long lastSentAt;
    private String lastAttemptIp;
    private Integer status;

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
}
