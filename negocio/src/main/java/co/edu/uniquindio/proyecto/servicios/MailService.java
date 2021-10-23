package co.edu.uniquindio.proyecto.servicios;

public interface MailService {

    void sendMail(String from, String to, String subject, String body);
}
