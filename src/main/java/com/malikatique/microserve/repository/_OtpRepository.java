//package com.malikatique.microserve.repository;
//
//import com.malikatique.microserve.models._Otp;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface _OtpRepository extends MongoRepository<_Otp, String> {
//    @Query("{ 'verificationType': ?0, 'email': ?1, 'validFor': ?2, 'lastAttemptIp': ?3, 'status': ?4 }")
//    _Otp findOtpByEmail(String verificationType, String email, String validFor, String lastAttemptIp, Integer status);
//
//
//    @Query("{ 'verificationType': ?0, 'countryCode': ?1, 'phone': ?2, 'validFor': ?3, 'lastAttemptIp': ?4, 'status': ?5 }")
//    _Otp findOtpByPhone(String verificationType, String countryCode, String phone, String validFor, String lastAttemptIp, Integer status);
//
//}