package com.niit.mailSender.component;

import com.niit.mailSender.model.EmailDetails;
import com.niit.mailSender.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "mail_queue")
    public void getMail(EmailDTO emailDTO)
    {
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setReceiver(emailDTO.getReceiver());
        emailDetails.setSubject(emailDTO.getSubject());
        emailDetails.setMessageBody(emailDTO.getMessageBody());
        emailService.sendEmail(emailDetails);

    }

}
