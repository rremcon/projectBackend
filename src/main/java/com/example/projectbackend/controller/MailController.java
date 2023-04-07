package com.example.projectbackend.controller;
import com.example.projectbackend.model.Mail;
import com.example.projectbackend.repository.MailRepository;
import com.example.projectbackend.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MailController {

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private MailService emailService;


    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody Mail mail)
    {
        String status = emailService.sendMail(mail);
        return status;
    }


    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody Mail mail)
    {
        String status = emailService.sendMailWithAttachment(mail);
        return status;
    }
}
