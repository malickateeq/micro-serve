package com.malikatique.microserve.repository;

import com.malikatique.microserve.models.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    @Query("{ 'user': ?0}")
    RefreshToken findExistingRefreshToken(String user);

    @Query("{ 'refreshToken': ?0}")
    RefreshToken findByRefreshToken(String token);

    void deleteByRefreshToken(String refreshToken);
}
