package com.example.projectbackend.service;
import com.example.projectbackend.model.Mail;
import com.example.projectbackend.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService implements MailRepository {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    public String sendMail(Mail mail)
    {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(mail.getRecipient());
            mailMessage.setText(mail.getMessage());
            mailMessage.setSubject(mail.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail send successfully...";
        }

        catch (Exception e) {
            return "Error with sending mail";
        }
    }

    public String
    sendMailWithAttachment(Mail mail)
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(mail.getRecipient());
            mimeMessageHelper.setText(mail.getMessage());
            mimeMessageHelper.setSubject(mail.getSubject());

            FileSystemResource file = new FileSystemResource(
                    new File(mail.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            return "Mail send successfully...";
        }

        catch (MessagingException e) {

            return "Error with sending mail";
        }
    }

}

