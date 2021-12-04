package br.com.allangf.BlogAPI.rest.Service.impl;

import br.com.allangf.BlogAPI.domain.entity.Email;
import br.com.allangf.BlogAPI.domain.exception.RuleOfException;
import br.com.allangf.BlogAPI.domain.repository.EmailRepository;
import br.com.allangf.BlogAPI.rest.Errors;
import br.com.allangf.BlogAPI.rest.Service.EmailService;
import br.com.allangf.BlogAPI.rest.config.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public void send(String title, String body) {


        try {
            List<String> emails = emailRepository.findAll().stream().map(Email::getEmail).collect(Collectors.toList());

            if (emails.isEmpty()) {
                return;
            }

            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emails.toArray(new String[0]));
            email.setSubject(title);
            email.setText(body);
            mailSender.send(email);
        } catch (MailSendException e) {
            throw new RuleOfException(Errors.EMAIL_ERROR);
        } catch (MailAuthenticationException e) {
            throw new RuleOfException(Errors.EMAIL_LOGIN_ERROR);
        }
    }

    @Override
    public void addEmailList(EmailDTO emailDTO) {

        List<Email> existEmail = emailRepository.findByEmail(emailDTO.getEmail());
        if (!existEmail.isEmpty()) {
            throw new RuleOfException(Errors.EMAIL_ALREADY_REGISTERED);
        }

        Email email = new Email();
        email.setEmail(emailDTO.getEmail());

        emailRepository.save(email);
    }

    @Override
    public void removeEmailList(EmailDTO emailDTO) {

        try {
            Email email = emailRepository.findByEmail(emailDTO.getEmail()).get(0);
            emailRepository.deleteById(email.getEmailId());
        } catch (IndexOutOfBoundsException e) {
            throw new RuleOfException(Errors.EMAIL_NOT_FOUND);
        }
    }
}
