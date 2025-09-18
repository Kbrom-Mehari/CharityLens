package com.kbrom.charity_lens_backend.common.service;

import com.kbrom.charity_lens_backend.common.dto.EmailRequest;

public interface EmailService {
    void sendEmail(EmailRequest emailRequest);
}
