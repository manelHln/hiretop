package com.backend.hiretop.service;

import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill createSkill(String skill, Job job) {
        if (skillRepository.existsByName(skill)) {
            return skillRepository.findByName(skill);
        }
        Skill newSkill = Skill.builder().name(skill).build();
        newSkill.getJobs().add(job);
        return skillRepository.save(newSkill);
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill updateSkill(Long id, Skill skillDetails) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new RuntimeException("Skill not found"));
        skill.setName(skillDetails.getName());
        skill.setIcon(skillDetails.getIcon());
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}