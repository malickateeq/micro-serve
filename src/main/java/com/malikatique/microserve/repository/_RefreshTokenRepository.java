package com.malikatique.microserve.repository;

import com.malikatique.microserve.models._RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface _RefreshTokenRepository extends MongoRepository<_RefreshToken, String> {
    @Query("{ 'user': ?0}")
    _RefreshToken findExistingRefreshToken(String user);

    @Query("{ 'refreshToken': ?0}")
    _RefreshToken findByRefreshToken(String token);

    void deleteByRefreshToken(String refreshToken);
}
