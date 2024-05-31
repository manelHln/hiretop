package com.backend.hiretop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hiretop.domain.Applicant;
import com.backend.hiretop.domain.Company;
import com.backend.hiretop.domain.Message;
import com.backend.hiretop.service.ApplicantService;
import com.backend.hiretop.service.CompanyService;
import com.backend.hiretop.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/applicant-to-company/{companyId}")
    public ResponseEntity<Message> sendMessageFromApplicantToCompany(
            @AuthenticationPrincipal Applicant senderApplicant,
            @PathVariable Long companyId,
            @RequestBody String content) {
        Company recipientCompany = companyService.findById(companyId);
        Message message = messageService.sendMessage(senderApplicant, recipientCompany, content);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/company-to-applicant/{applicantId}")
    public ResponseEntity<Message> sendMessageFromCompanyToApplicant(
            @AuthenticationPrincipal Company senderCompany,
            @PathVariable Long applicantId,
            @RequestBody String content) {
        Applicant recipientApplicant = applicantService.findById(applicantId);
        Message message = messageService.sendMessage(senderCompany, recipientApplicant, content);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/applicant/unread")
    public ResponseEntity<Long> countUnreadMessagesForApplicant(@AuthenticationPrincipal Applicant recipientApplicant) {
        long count = messageService.countUnreadMessagesForApplicant(recipientApplicant);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/company/unread")
    public ResponseEntity<Long> countUnreadMessagesForCompany(@AuthenticationPrincipal Company recipientCompany) {
        long count = messageService.countUnreadMessagesForCompany(recipientCompany);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/applicant")
    public ResponseEntity<List<Message>> getAllMessagesForApplicant(
            @AuthenticationPrincipal Applicant recipientApplicant) {
        List<Message> messages = messageService.getAllMessagesForApplicant(recipientApplicant);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/company")
    public ResponseEntity<List<Message>> getAllMessagesForCompany(@AuthenticationPrincipal Company recipientCompany) {
        List<Message> messages = messageService.getAllMessagesForCompany(recipientCompany);
        return ResponseEntity.ok(messages);
    }
}
