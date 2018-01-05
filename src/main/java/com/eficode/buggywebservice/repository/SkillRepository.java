package com.eficode.buggywebservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eficode.buggywebservice.domain.Skill;

/**
 * Skill DAO
 */
public interface SkillRepository extends MongoRepository<Skill, String> {
}