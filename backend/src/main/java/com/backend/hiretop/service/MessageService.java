package com.backend.hiretop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Message;
import com.backend.hiretop.repository.ApplicantRepository;
import com.backend.hiretop.repository.CompanyRepository;
import com.backend.hiretop.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Message sendMessage(Applicant senderApplicant, Company recipientCompany, String content) {
        // Because this error: "detached entity passed to persist: com.backend.hiretop.domain.Applicant"
        Applicant applicant = applicantRepository.findById(senderApplicant.getId()).get();
        Message message = Message.builder()
                .senderApplicant(applicant)
                .recipientCompany(recipientCompany)
                .content(content)
                .timestamp(new Date())
                .build();
        return messageRepository.save(message);
    }

    public Message sendMessage(Company senderCompany, Applicant recipientApplicant, String content) {
        Company company = companyRepository.findById(senderCompany.getId()).get();
        Message message = Message.builder()
                .senderCompany(company)
                .recipientApplicant(recipientApplicant)
                .content(content)
                .timestamp(new Date())
                .build();
        return messageRepository.save(message);
    }

    public long countUnreadMessagesForApplicant(Applicant recipientApplicant) {
        return messageRepository.findByRecipientApplicantAndReadFalse(recipientApplicant).size();
    }

    public long countUnreadMessagesForCompany(Company recipientCompany) {
        return messageRepository.findByRecipientCompanyAndReadFalse(recipientCompany).size();
    }

    public List<Message> getAllMessagesForApplicant(Applicant recipientApplicant) {
        return messageRepository.findByRecipientApplicant(recipientApplicant);
    }

    public List<Message> getAllMessagesForCompany(Company recipientCompany) {
        return messageRepository.findByRecipientCompany(recipientCompany);
    }
}
