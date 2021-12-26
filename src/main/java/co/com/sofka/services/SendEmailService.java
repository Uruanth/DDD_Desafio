package co.com.sofka.services;

public interface SendEmailService {
    boolean sendEmail(String email,String Id, String aggretaName, String body);
}
