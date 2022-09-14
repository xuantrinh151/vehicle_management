package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.dto.sdi.Email;

public interface EmailService {

    String sendSimpleMail(Email details);

    String sendMailWithAttachment(Email details);
}
