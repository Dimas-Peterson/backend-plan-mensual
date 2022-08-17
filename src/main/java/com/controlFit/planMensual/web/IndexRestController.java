package com.controlFit.planMensual.web;

import com.alibaba.fastjson.JSONObject;
import com.controlFit.planMensual.service.MailService;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class IndexRestController {
    
    @Autowired
    private MailService mailService;
    
    @PostMapping("/datosFormulario")
    public String enviarDatosFormulario(@RequestBody JSONObject param) throws MessagingException, UnsupportedEncodingException{
        
        //Catching data
        String customerName = param.getString("name");
        String customerLastName = param.getString("lastname");
        String gymName = param.getString("gymname");
        String customerEmail = param.getString("email");
        String customerPhone = param.getString("phone");
        String range = param.getString("range");
        boolean notiWhatsapp = param.getBoolean("notiWhatsapp");
        boolean appMember = param.getBoolean("appMember");
        boolean appOwner = param.getBoolean("appOwner");
        boolean extraComputer = param.getBoolean("extraComputer");
        
        //Calculating price
        double initialPrice = 2700;
        double addedPrice = 0;
        double finalPrice = 0;

        switch(range){
            case "100":
                addedPrice = 0;
                break;
            case "200":
                addedPrice = 400;
                break;
            case "300":
                addedPrice = 800;
                break;
            case "400":
                addedPrice = 1200;
                break;
            case "500":
                addedPrice = 1600;
                break;
            case "600":
                addedPrice = 2000;
                break;
            case "700":
                addedPrice = 2400;
                break;
            case "800":
                addedPrice = 2800;
                break;
            case "900":
                addedPrice = 3200;
                break;
            case "1000":
                addedPrice = 3600;
                break;
        }
    
        if (notiWhatsapp) {
            addedPrice += 1500;
        }

        if (appMember) {
            addedPrice += 1900;
        }

        if (appOwner) {
            addedPrice += 1200;
        }

        if (extraComputer) {
            addedPrice += 700;
        }

        finalPrice = initialPrice + addedPrice;
        
        //Sending mails
        mailService.mailToCustomer(customerName, gymName, range, notiWhatsapp, appMember, appOwner, extraComputer, customerEmail, finalPrice);
        mailService.mailToOwner(customerName, customerLastName, customerPhone, customerEmail, gymName, range, notiWhatsapp, appMember, appOwner, extraComputer);
        
    
    return customerName+customerLastName;
    }
}
