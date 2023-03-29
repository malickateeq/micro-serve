package com.malikatique.microserve.repository;

import com.malikatique.microserve.models._User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface _UserRepository extends MongoRepository<_User, String> {

    _User findByEmail(String email);

    @Query("{ 'countryCode': ?0, 'phone': ?1}")
    _User findByPhone(String countryCode, String phone);

}
