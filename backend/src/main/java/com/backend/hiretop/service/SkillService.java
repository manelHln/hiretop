package com.backend.hiretop.service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Job;
import com.backend.hiretop.domain.Skill;
import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill createSkill(String skillName, Job job) {
        Skill skill = skillRepository.findByName(skillName)
                .orElseGet(() -> {
                    Skill newSkill = new Skill();
                    newSkill.setName(skillName);
                    skillRepository.save(newSkill);
                    return newSkill;
                });
        if (!skill.getJobs().contains(job)) {
            skill.getJobs().add(job);
            job.getSkills().add(skill);
        }
        return skillRepository.save(skill);
    }

    public Skill createSkill(String skillName, Applicant applicant) {
        Skill skill = skillRepository.findByName(skillName)
                .orElseGet(() -> {
                    Skill newSkill = new Skill();
                    newSkill.setName(skillName);
                    skillRepository.save(newSkill);
                    return newSkill;
                });
        if (!skill.getApplicants().contains(applicant)) {
            skill.getApplicants().add(applicant);
            applicant.getSkills().add(skill);
        }
        return skillRepository.save(skill);
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public ResponseVO<List<Skill>> getAllSkills() {
        return new ResponseVOBuilder<List<Skill>>().success().addData(skillRepository.findAll()).build();
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