package com.example.projectbackend.repository;
import com.example.projectbackend.model.Mail;

public interface MailRepository {
    String sendMail(Mail mail);
    String sendMailWithAttachment(Mail mail);

}
