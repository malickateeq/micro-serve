package com.malikatique.microserve.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "access_tokens")
public class RefreshToken {
    @Id
    private String id;

    @DBRef
    private User user;

    private String refreshToken;
    private Date expirationDate;

    private Date issuedAt;

    private int status;
}
