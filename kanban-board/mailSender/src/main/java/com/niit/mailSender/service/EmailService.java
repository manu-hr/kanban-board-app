package com.niit.mailSender.service;

import com.niit.mailSender.model.EmailDetails;

public interface EmailService {
    public abstract String sendEmail(EmailDetails emailDetails);
}
