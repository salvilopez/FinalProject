package com.ssl.finalproject.service.impl;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnvioEmailService {
    @Autowired
    private JavaMailSender mailSender;

    //Pasamos por parametro: destinatario, asunto y el mensaje
    public void sendEmail(String to, String subject, String content) {

    /*    SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);

        mailSender.send(email);*/


        String API_KEY = "d6ee8bd2b437eb96f4d635fbebd9a570588aa1e2";
        Client client = new Client(API_KEY);

        try {
            client.sendMessage(
                    "salvilopezpum@gmail.com",
                    to,
                    subject,
                    content,
                    "");
        } catch (SparkPostException e) {
            e.printStackTrace();
        }
    }
}
