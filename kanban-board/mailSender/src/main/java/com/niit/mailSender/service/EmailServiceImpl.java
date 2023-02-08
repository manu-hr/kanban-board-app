package com.niit.mailSender.service;

import com.niit.mailSender.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.beans.JavaBean;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private  String sender;
    @Override
    public String sendEmail(EmailDetails emailDetails) {
        try
        {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(emailDetails.getReceiver());
            simpleMailMessage.setSubject(emailDetails.getSubject());
            simpleMailMessage.setText(emailDetails.getMessageBody());
            javaMailSender.send(simpleMailMessage);
            String msg="Mail sent Successfully";
            return msg;

        }
        catch (Exception e)
        {
            String msg="Some Errors";
            return msg;

        }

    }
}
