package com.eficode.buggywebservice.repository;

import com.eficode.buggywebservice.domain.EmployeeInformation;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Why this is only witch is needed to use mongodb with Spring Boot?
 */

/*
 * Answer:
 * Spring Data creates the following default query methods and if they are sufficient then there is no need to add custom methods:
 * - findAll, count, delete, deleteAll, deleteAll, deleteById, existsById, findAllById, findById, save, count, exists, findAll, findOne
 * NOTE: Spring Boot takes care of the configurations of MongoTemplate and MongoRepository.
 * 
 * https://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/repository/MongoRepository.html
 */

public interface JobRepository extends MongoRepository<EmployeeInformation,String> {
}