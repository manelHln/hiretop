package com.backend.hiretop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRecipientApplicantAndReadFalse(Applicant recipientApplicant);
    List<Message> findByRecipientCompanyAndReadFalse(Company recipientCompany);
    List<Message> findByRecipientApplicant(Applicant recipientApplicant);
    List<Message> findByRecipientCompany(Company recipientCompany);
}
