package com.eficode.buggywebservice.domain;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Skill {
	
	// If the value of the @Id field is not null, it’s stored in the database as-is - 
	// otherwise, the converter will assume you want to store an ObjectId in the DB
	@Id
    public String id;
    public final String name;
    public final ExperienceLevel experienceLevel;

    /*
    Write builder for this class and tester for it.
     */
    @JsonCreator
    public Skill(@JsonProperty("name") String name, @JsonProperty("experienceLevel") ExperienceLevel experienceLevel) {
        this.name = name;
        this.experienceLevel = experienceLevel;
    }
    
    public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	/**
     * Builder instance
     * @return builder
     */
    public static Builder getBuilder() {
        return new Builder();
    }
    
    /**
     * Static nested Skill builder class
     */
    public static class Builder {

        private String name;
        private ExperienceLevel experienceLevel;
        
        private	Builder() {}

        public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder experienceLevel(ExperienceLevel experienceLevel) {
			this.experienceLevel = experienceLevel;
			return this;
		}

		public Skill build() {
            return new Skill(this.name, this.experienceLevel);
        }
    }
}
