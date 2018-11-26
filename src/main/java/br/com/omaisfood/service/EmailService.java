package br.com.omaisfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private static JavaMailSender mailSender;

    public Boolean sendEmail() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "rafaelsilvestre.183@gmail.com" );
            helper.setSubject( "O Mais Food - Pedido Nº 13589 realizado com sucesso!" );
            helper.setText("<p>Hello from Spring Boot Application</p>", true);
            mailSender.send(mail);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
