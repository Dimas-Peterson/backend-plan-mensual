package com.controlFit.planMensual.service;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Currency;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    
    
    @Autowired
    private JavaMailSender mailSender;
    
    
    public void mailToCustomer(
            String customerName,
            String gymName,
            String range,
            boolean notiWhatsapp,
            boolean appMember,
            boolean appOwner,
            boolean extraComputer,
            String email,
            double finalPrice) throws MessagingException, UnsupportedEncodingException{
        
        String subject = "Este mail es para el cliente";
        String senderName = "Control Fit";
        String mailContent = "<h2>Hola, "+ customerName + "!</h2>";
        mailContent+="<p>Gracias por detallarnos los datos de tu plan</p>";
        mailContent+="<p>Te dejamos acá el listado de lo que elegiste:</p>";
        mailContent+="<ul>"
                + "<li>Nombre del gimnasio: "+gymName+"</li>"
                + "<li>Cupo de socios activos: " + range + "</li>";
        if (notiWhatsapp) mailContent+="<li>Notificaciones por Whatsapp: Si</li>";
        else mailContent+="<li>Notificaciones por Whatsapp: No</li>";
        
        if (appMember) mailContent+="<li>App móvil para socios: Si</li>";
        else mailContent+="<li>App móvil para socios: No</li>";
        
        if (appOwner) mailContent+="<li>App móvil para dueños: Si</li>";
        else mailContent+="<li>App móvil para dueños: No</li>";
        
        if (extraComputer) mailContent+="<li>Computadora extra: Si</li>";
        else mailContent+="<li>Computadora extra: No</li>";
        
        mailContent+="</ul>";
        
        var formatter = NumberFormat.getInstance();
        formatter.setCurrency(Currency.getInstance("ARS"));
        formatter.setMinimumFractionDigits(2);
        String finalPriceStr = formatter.format(finalPrice);
        
        mailContent+= "<p>Precio final del plan: AR$"+ finalPriceStr +"</p>";
        
        mailContent+="<p>Próximamente nos pondremos en contacto!</p>";
        mailContent+="<p>Saludos, <strong>"+senderName+"</strong></p>";
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("alejandro.nahue.15817@gmail.com",senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        
        mailSender.send(message);
    }
    
    
    public void mailToOwner(
            String customerName,
            String customerLastName,
            String customerPhone,
            String customerEmail,
            String gymName,
            String range,
            boolean notiWhatsapp,
            boolean appMember,
            boolean appOwner,
            boolean extraComputer) throws MessagingException, UnsupportedEncodingException{
        
        String [] to = new String[2];
        to[0] = "alejandro.nahuel.15817@gmail.com";
        to[1] = "robertodipeterson@gmail.com";
        
        String subject = "Este mail es para el product owner";
        String senderName = "Control Fit";
        String mailContent = "<h2>Hola, "+customerName+" ha solicitado el siguiente plan de servicios: </h2>";
        mailContent += "<p><strong>Detalles:</strong></p>";
        mailContent += "<ul>"
                + "<li>Nombre: "+ customerName + "</li>"
                + "<li>Apellido: "+ customerLastName + "</li>"
                + "<li>Telefono de contacto: "+ customerPhone + "</li>"
                + "<li>Email de contacto: "+ customerEmail + "</li>"
                + "<li>Nombre del gimnasio: " + gymName + "</li>"
                + "<li>Cupo de socios activos:" + range + "</li>";
        if (notiWhatsapp) mailContent+="<li>Notificaciones por Whatsapp: Si</li>";
        else mailContent+="<li>Notificaciones por Whatsapp: No</li>";
        
        if (appMember) mailContent+="<li>App móvil para socios: Si</li>";
        else mailContent+="<li>App móvil para socios: No</li>";
        
        if (appOwner) mailContent+="<li>App móvil para dueños: Si</li>";
        else mailContent+="<li>App móvil para dueños: No</li>";
        
        if (extraComputer) mailContent+="<li>Computadora extra: Si</li>";
        else mailContent+="<li>Computadora extra: No</li>";
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("alejandro.nahue.15817@gmail.com", senderName);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        
        mailSender.send(message);
    }
    
}
