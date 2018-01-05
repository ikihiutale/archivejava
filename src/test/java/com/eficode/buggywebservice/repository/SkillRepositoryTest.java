package com.eficode.buggywebservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eficode.buggywebservice.domain.ExperienceLevel;
import com.eficode.buggywebservice.domain.Skill;

import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.stream.StreamSupport;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillRepositoryTest {

    @Autowired
    private SkillRepository skillRepository;
    
    // @Transactional
	// @Rollback(false)
    @Test
    public void insertAndReceiveSkillCreatedWithBuilder() {
    	final String skillName = "Java";
        final ExperienceLevel skillExperienceLevel = ExperienceLevel.AVERAGE;
        Skill entity = Skill.getBuilder().name(skillName).experienceLevel(skillExperienceLevel).build();
        entity = skillRepository.save(entity);
        assertThat(entity.getId(), not(isEmptyOrNullString()));
        assertThat(entity.getName(), is(skillName));
        assertThat(entity.getExperienceLevel(), is(skillExperienceLevel));
        skillRepository.delete(entity);
    }

    // @Transactional
	// @Rollback(false)
    @Test
    public void insertSomeAndReceiveFirst() {
    	skillRepository.save(Skill.getBuilder().name("Java").experienceLevel(ExperienceLevel.AVERAGE).build());
    	skillRepository.save(Skill.getBuilder().name("C++").experienceLevel(ExperienceLevel.SENIOR).build());
    	skillRepository.save(Skill.getBuilder().name("Kotlin").experienceLevel(ExperienceLevel.BEGINNER).build());
        List<Skill> all = skillRepository.findAll();
        
        assertThat(all, not(IsEmptyCollection.empty()));

        final Skill entity = StreamSupport.stream(all.spliterator(), false).findFirst().get();
        assertThat(entity, notNullValue());
        
        skillRepository.delete(all);
        all = skillRepository.findAll();
        assertThat(all, IsEmptyCollection.empty());
    }
}
