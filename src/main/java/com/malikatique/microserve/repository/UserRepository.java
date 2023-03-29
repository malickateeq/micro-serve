package com.malikatique.microserve.repository;

import com.malikatique.microserve.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    @Query("{ 'countryCode': ?0, 'phone': ?1}")
    User findByPhone(String countryCode, String phone);

}
