package com.trinhnx151.vehicle_management.controllers;

import com.trinhnx151.vehicle_management.dto.sdi.Email;
import com.trinhnx151.vehicle_management.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody Email details) {
        return emailService.sendSimpleMail(details);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody Email details) {
        return emailService.sendMailWithAttachment(details);
    }
}
